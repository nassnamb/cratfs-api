package com.nwn.crafts.core.services;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.models.Craftsman;
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

import static java.lang.String.format;

@Service
public class CraftsmanService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CraftsmanRepository craftsmanRepository;

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

    public Craftsman save(CraftsmanDto craftsmanDto) {
        var craftsman = new Craftsman();
        craftsman.setCraftsmanName(craftsmanDto.getCraftsmanName());
        return craftsmanRepository.saveAndFlush(craftsman);
    }

    public Craftsman updateCraftsman(Long craftsmanId, CraftsmanDto craftsmanDto) throws CraftsException {
        Objects.requireNonNull(craftsmanDto);
        logger.debug("Updating craftsman with id : {}", craftsmanId);
        Craftsman current = findById(craftsmanId);
        if(current != null) {
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
        CraftsmanDto craftsmanDto = new CraftsmanDto();
        craftsmanDto.setCraftsmanId(craftsman.getCraftsmanId());
        craftsmanDto.setCraftsmanName(craftsman.getCraftsmanName());
        return craftsmanDto;
    }
}
