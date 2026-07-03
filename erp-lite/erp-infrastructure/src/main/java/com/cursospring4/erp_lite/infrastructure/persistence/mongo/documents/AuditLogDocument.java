package com.cursospring4.erp_lite.infrastructure.persistence.mongo.documents;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * Documento MongoDB que mapea la colección {@code audit_logs}.
 * Registra trazabilidad de operaciones del sistema (append-only).
 */
@Document(collection = "audit_logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class AuditLogDocument {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Indexed
    private String userId;

    private String className;

    private String methodName;

    private String endpoint;

    private String ipAddress;

    private boolean success;

    private String errorMessage;

    private Long executionTimeMs;

    @Indexed
    private Instant timestamp;
}
