package com.cursospring4.erp_lite.infrastructure.persistence.sql.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Entidad JPA que mapea la tabla {@code public.products}.
 *
 * <p>Puntos clave del mapeo:
 * <ul>
 *   <li>PK UUID generada por Hibernate.
 *   <li>{@code sku} tiene constraint UNIQUE.
 *   <li>{@code description} e {@code image_url} son nullable.
 *   <li>{@code description} usa {@code columnDefinition = "text"} para mapear el tipo TEXT de PG.
 *   <li>{@code active} es un {@link Boolean} con default {@code true}.
 *   <li>La relación con {@link OrderProductEntity} es lazy y sin cascade.
 * </ul>
 */
@Entity
@Table(
    name    = "products",
    schema  = "public",
    uniqueConstraints = @UniqueConstraint(
        name        = "uq_products_sku",
        columnNames = "sku"
    )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "orderProducts")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "sku", length = 50, nullable = false, unique = true)
    private String sku;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "price", nullable = false, precision = 15, scale = 2)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    @ColumnDefault("0")
    @Builder.Default
    private Integer stock = 0;

    @Column(name = "category_id", length = 100)
    private String categoryId;

    @Column(name = "image_url", length = 500)
    private String imageUrl;

    @Column(name = "active", nullable = false)
    @ColumnDefault("true")
    @Builder.Default
    private Boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(
        mappedBy = "product",
        fetch    = FetchType.LAZY
    )
    @Builder.Default
    private List<OrderProductEntity> orderProducts = new ArrayList<>();
}
