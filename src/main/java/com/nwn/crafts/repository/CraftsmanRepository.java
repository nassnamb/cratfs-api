package com.nwn.crafts.repository;

import com.nwn.crafts.core.domain.Craftsman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CraftsmanRepository extends JpaRepository<Craftsman, Long> {
}
