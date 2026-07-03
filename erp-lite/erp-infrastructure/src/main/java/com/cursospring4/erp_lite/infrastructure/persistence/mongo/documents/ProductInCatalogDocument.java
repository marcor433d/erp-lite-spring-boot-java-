package com.cursospring4.erp_lite.infrastructure.persistence.mongo.documents;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * Documento MongoDB que mapea la colección {@code product_documents}.
 */
@Document(collection = "product_documents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductInCatalogDocument {

    @Id
    @EqualsAndHashCode.Include
    private String id;

    @Indexed(unique = true)
    private String sku;

    private String name;

    private String description;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal price;

    private String currency;

    private Integer stock;

    @Indexed
    private String categoryId;

    private String categoryName;

    private String imageUrl;

    @Builder.Default
    private boolean active = true;

    private Map<String, String> specifications;

    private List<String> tags;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
