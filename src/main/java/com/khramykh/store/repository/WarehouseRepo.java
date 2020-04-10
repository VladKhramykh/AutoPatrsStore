package com.khramykh.store.repository;

import com.khramykh.store.domain.orgs.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepo extends JpaRepository<Warehouse, Long> {
}
