package com.nwn.crafts.repository;

import com.nwn.crafts.core.models.Category;
import com.nwn.crafts.core.models.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
