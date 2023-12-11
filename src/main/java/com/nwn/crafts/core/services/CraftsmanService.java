package com.nwn.crafts.core.services;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.domain.Craftsman;
import com.nwn.crafts.core.util.JSONTools;
import com.nwn.crafts.dto.CraftsmanDto;
import com.nwn.crafts.repository.CraftsmanRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.nwn.crafts.core.models.AuditType.CREATE_CRAFTSMAN;
import static java.lang.String.format;

@Service
public class CraftsmanService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CraftsmanRepository craftsmanRepository;

    @Autowired
    private AuditService auditService;

    public List<CraftsmanDto> findAll() {
        return craftsmanRepository.findAll().stream()
                .map(this::mapToCraftsmanDto)
                .collect(Collectors.toList());
    }

    public CraftsmanDto getById(Long id) {
        return craftsmanRepository.findById(id).map(this::mapToCraftsmanDto).orElse(null);
    }

    private Craftsman findById(Long id) {
        return craftsmanRepository.findById(id).orElse(null);
    }

    public Craftsman create(CraftsmanDto craftsmanDto) {
        var craftsman = new Craftsman();
        craftsman.setCraftsmanName(craftsmanDto.getCraftsmanName());
        var userLoginByDefault = "Admin";
        var objetJson = JSONTools.pojoToJSONString(craftsman);
        Craftsman newCraftsman = null;
        try {
            newCraftsman = craftsmanRepository.saveAndFlush(craftsman);
            objetJson = JSONTools.pojoToJSONString(newCraftsman);
            auditService.saveAudit(craftsmanDto.getCraftsmanName(), userLoginByDefault, CREATE_CRAFTSMAN, objetJson, true);
            logger.info("Craftsman '{}'  is successfully created", craftsmanDto.getCraftsmanName());
        } catch (Exception e) {
            logger.error("Failed to create Craftsman with name: '{}'", craftsmanDto.getCraftsmanName());
            logger.error(e.toString());
            auditService.saveAudit(craftsmanDto.getCraftsmanName(), userLoginByDefault, CREATE_CRAFTSMAN, objetJson, false);
        }
        return newCraftsman;
    }

    public Craftsman updateCraftsman(Long craftsmanId, CraftsmanDto craftsmanDto) throws CraftsException {
        Objects.requireNonNull(craftsmanDto);
        logger.debug("Updating craftsman with id : {}", craftsmanId);
        Craftsman current = findById(craftsmanId);
        if (current != null) {
            BeanUtils.copyProperties(craftsmanDto, current, "craftsmanId");
        } else {
            throw new CraftsException(format("No craftsman found with id: %s", craftsmanId));
        }
        return craftsmanRepository.saveAndFlush(current);
    }

    public void deleteById(Long id) {
        craftsmanRepository.deleteById(id);
    }

    private CraftsmanDto mapToCraftsmanDto(Craftsman craftsman) {
        var craftsmanDto = new CraftsmanDto();
        craftsmanDto.setCraftsmanId(craftsman.getCraftsmanId());
        craftsmanDto.setCraftsmanName(craftsman.getCraftsmanName());
        return craftsmanDto;
    }
}
