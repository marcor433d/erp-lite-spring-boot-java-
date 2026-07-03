package com.cursospring4.erp_lite.infrastructure.persistence.mongo.documents;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.cursospring4.erp_lite.infrastructure.enums.CatalogType;

import java.time.Instant;
import java.util.List;

/**
 * Documento MongoDB que mapea la colección {@code catalogs}.
 */
@Document(collection = "catalogs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CatalogDocument {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    private String name;

    private String description;

    @Field("active")
    private boolean active;

    @Indexed
    private CatalogType catalogType;

    private List<CatalogItem> items;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
