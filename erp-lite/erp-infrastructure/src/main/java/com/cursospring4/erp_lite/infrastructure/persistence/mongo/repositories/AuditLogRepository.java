package com.cursospring4.erp_lite.infrastructure.persistence.mongo.repositories;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cursospring4.erp_lite.infrastructure.persistence.mongo.documents.AuditLogDocument;

public interface AuditLogRepository extends MongoRepository<AuditLogDocument, ObjectId> {

}
