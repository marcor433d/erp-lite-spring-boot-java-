package com.cursospring4.erp_lite.infrastructure.persistence.sql.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursospring4.erp_lite.infrastructure.persistence.sql.entities.OrderEntity;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {

}
