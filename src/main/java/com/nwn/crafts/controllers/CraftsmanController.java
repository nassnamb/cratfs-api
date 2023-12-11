package com.nwn.crafts.controllers;


import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.domain.CraftsmanNotFountException;
import com.nwn.crafts.core.domain.Craftsman;
import com.nwn.crafts.core.services.CraftsmanService;
import com.nwn.crafts.dto.CraftsmanDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/craftsmen")
public class CraftsmanController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CraftsmanService craftsmanService;


    @Operation(summary = "Get all craftsmen", description = "Returns the list of all craftsmen in Database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No craftsman found")
    })
    @GetMapping("/")
    public List<CraftsmanDto> list() {
        return craftsmanService.findAll();
    }


    @Operation(summary = "Get a craftsman by id", description = "Returns a craftsman from his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No craftsman was found for this id")
    })
    @GetMapping("{id}")
    public CraftsmanDto get(@PathVariable Long id) {
        return craftsmanService.getById(id);
    }


    @Operation(summary = "Create new Craftsman", description = "Create and save new Craftsman")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "No Craftsman is created")
    })
    @PostMapping("/create")
    public Craftsman create(@RequestBody final CraftsmanDto craftsman) {
        return craftsmanService.create(craftsman);
    }


    @Operation(summary = "Update Craftsman", description = "Update an existing craftsman's information from his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not found - No craftsman was found for this id")
    })
    @PutMapping("/update/{id}")
    public Craftsman update(@PathVariable Long id, @RequestBody final CraftsmanDto craftsmanDto) {
        try {
            return craftsmanService.updateCraftsman(id, craftsmanDto);
        } catch (CraftsException e) {
            logger.error("Error updating craftsman with id: {}", id);
            throw new CraftsmanNotFountException(e.getMessage());
        }
    }

    @Operation(summary = "Delete Craftsman", description = "Delete an existing craftsman from his id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - No craftsman was found for this id")
    })
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        craftsmanService.deleteById(id);
    }


}
