package com.khramykh.store.repository;

import com.khramykh.store.domain.orgs.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepo extends JpaRepository<Company, Long> {
}
