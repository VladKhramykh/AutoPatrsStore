package com.khramykh.store.repository;

import com.khramykh.store.domain.orgs.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepo extends JpaRepository<Supplier, Long> {
}
