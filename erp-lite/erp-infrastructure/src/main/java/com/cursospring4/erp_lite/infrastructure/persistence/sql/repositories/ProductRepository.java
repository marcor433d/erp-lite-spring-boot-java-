package com.cursospring4.erp_lite.infrastructure.persistence.sql.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cursospring4.erp_lite.infrastructure.persistence.sql.entities.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {

}
