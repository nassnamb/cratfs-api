package com.nwn.crafts.controllers;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.models.Category;
import com.nwn.crafts.core.models.ProfileNotFoundException;
import com.nwn.crafts.core.services.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/categories")
public class CategoryController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CategoryService categoryService;


    @Operation(summary = "Get all categories", description = "Returns the list of all categories in Database ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No category found")
    })
    @GetMapping("/")
    public List<Category> list() {
        return categoryService.findAll();
    }


    @Operation(summary = "Get a category Object by category", description = "Returns a category Object from his category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Not found - No category Object was found for this category id")
    })
    @GetMapping("{category}")
    public ResponseEntity<Category> get(@PathVariable Long category) {
        try {
            return ResponseEntity.ok(categoryService.findById(category));
        } catch (ProfileNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @Operation(summary = "Create new Category Object", description = "Create and save new Category Object")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created"),
            @ApiResponse(responseCode = "404", description = "No Category is created")
    })
    @PostMapping("/create")
    public Category create(@RequestBody final Category category) {
        return categoryService.addCategory(category);
    }


    @Operation(summary = "Update Category", description = "Update an existing Category's information from his category id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Updated"),
            @ApiResponse(responseCode = "404", description = "Not found - No Category Object was found for this Category's id")
    })
    @PutMapping("/update/{idCategory}")
    public Category update(@PathVariable Long idCategory, @RequestBody final Category category) {
        try {
            return categoryService.updateCategory(idCategory, category);
        } catch (CraftsException e) {
            logger.error("Error updating category Object with id: {}", idCategory);
            throw new ProfileNotFoundException(e.getMessage());
        }
    }

    @Operation(summary = "Delete Category", description = "Delete an existing Category's Object from his category's id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully Deleted"),
            @ApiResponse(responseCode = "404", description = "Not found - No Category's Object was found for this category's id")
    })
    @DeleteMapping("/delete/{idCategory}")
    public ResponseEntity<String> delete(@PathVariable Long idCategory) {
        categoryService.deleteById(idCategory);
        return ResponseEntity.ok("category deleted successfully");
    }
}
