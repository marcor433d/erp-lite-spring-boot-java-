package com.cursospring4.erp_lite.infrastructure.persistence.mongo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cursospring4.erp_lite.infrastructure.persistence.mongo.documents.CatalogDocument;

public interface CatalogRepository extends MongoRepository<CatalogDocument, String> {

}
