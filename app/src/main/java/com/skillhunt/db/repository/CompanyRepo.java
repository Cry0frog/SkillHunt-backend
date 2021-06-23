package com.skillhunt.db.repository;

import com.skillhunt.db.company.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<CompanyEntity, Long> {
}
