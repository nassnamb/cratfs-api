package com.nwn.crafts.core.services;

import com.nwn.crafts.core.domain.CraftsException;
import com.nwn.crafts.core.models.*;
import com.nwn.crafts.core.models.Category;
import com.nwn.crafts.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static java.lang.String.format;

@Service
public class CategoryService {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long idCategory) {
        return categoryRepository.findById(idCategory).orElseThrow(() -> new CategoryNotFoundException(format("No category found for this category id: %s", idCategory)));
    }

    public Category addCategory(Category category) {
        return categoryRepository.saveAndFlush(category);
    }

    public void deleteById(Long idCategory) {
        Objects.requireNonNull(idCategory, "category Id can not be null");
        if (findById(idCategory) != null) {
            categoryRepository.deleteById(idCategory);
        } else {
            throw new UserNotFoundException("le category " + idCategory + " est introuvable");
        }
    }

    public Category updateCategory(Long idCategory, Category category) throws CraftsException {
        Objects.requireNonNull(category);
        logger.debug("Updating category with id : {}", idCategory);
        Category current = findById(idCategory);
        if (current != null) {
            BeanUtils.copyProperties(category, current, "category");
        } else {
            throw new CraftsException(format("No category found with id: %s", idCategory));
        }
        return categoryRepository.saveAndFlush(current);
    }
}
