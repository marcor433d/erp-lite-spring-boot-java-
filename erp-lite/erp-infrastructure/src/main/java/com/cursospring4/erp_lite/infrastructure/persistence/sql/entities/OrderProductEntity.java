package com.cursospring4.erp_lite.infrastructure.persistence.sql.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Entidad JPA que mapea la tabla {@code public.order_products}.
 *
 * <p>Esta tabla representa el detalle de una orden (líneas de pedido).
 * No es un {@code @ManyToMany} simple porque tiene atributos propios
 * ({@code product_name}, {@code quantity}, {@code unit_price}, {@code subtotal}),
 * por lo que se modela como entidad independiente con su propia PK UUID.
 *
 * <h3>Foreign Keys y comportamiento en cascada:</h3>
 * <pre>
 *  FK                          ON DELETE    JPA / Hibernate
 *  ──────────────────────────  ──────────   ────────────────────────────────────────────
 *  fk_order_products_order     CASCADE      @OnDelete(CASCADE) + cascade=ALL en @OneToMany
 *  fk_order_products_product   RESTRICT     @OnDelete(NO_ACTION) — la BD bloquea el delete
 * </pre>
 */
@Entity
@Table(name = "order_products", schema = "public")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"order", "product"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name       = "order_id",
        nullable   = false,
        foreignKey = @ForeignKey(name = "fk_order_products_order")
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private OrderEntity order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name       = "product_id",
        nullable   = false,
        foreignKey = @ForeignKey(name = "fk_order_products_product")
    )
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private ProductEntity product;

    @Column(name = "product_name", length = 200, nullable = false)
    private String productName;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "unit_price", nullable = false, precision = 15, scale = 2)
    private BigDecimal unitPrice;

    @Column(name = "subtotal", nullable = false, precision = 15, scale = 2)
    private BigDecimal subtotal;

    public static OrderProductEntity of(OrderEntity order, ProductEntity product, int quantity) {
        BigDecimal unitPrice = product.getPrice();
        return OrderProductEntity.builder()
            .order(order)
            .product(product)
            .productName(product.getName())
            .quantity(quantity)
            .unitPrice(unitPrice)
            .subtotal(unitPrice.multiply(BigDecimal.valueOf(quantity)))
            .build();
    }
}
