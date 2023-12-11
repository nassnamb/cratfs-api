package com.nwn.crafts.repository;

import com.nwn.crafts.core.models.AuditLine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<AuditLine, Long> {
}
