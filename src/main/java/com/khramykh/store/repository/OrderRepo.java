package com.khramykh.store.repository;

import com.khramykh.store.domain.orgs.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
