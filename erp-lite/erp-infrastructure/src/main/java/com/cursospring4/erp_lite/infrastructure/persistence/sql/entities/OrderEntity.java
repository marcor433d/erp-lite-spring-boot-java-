package com.cursospring4.erp_lite.infrastructure.persistence.sql.entities;

import jakarta.persistence.*;
import com.cursospring4.erp_lite.infrastructure.enums.OrderStatus;
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
 * Entidad JPA que mapea la tabla {@code public.orders}.
 *
 * <p>Puntos clave del mapeo:
 * <ul>
 *   <li>PK UUID generada por Hibernate (estrategia UUID, equivalente a {@code uuid_generate_v4()}).
 *   <li>{@code order_number} tiene constraint UNIQUE reflejado en {@code @UniqueConstraint}.
 *   <li>{@code status} usa {@link EnumType#STRING} para guardar el literal ('PENDING', etc.).
 *   <li>{@code created_at} y {@code updated_at} son manejados automáticamente por Hibernate
 *       con {@link CreationTimestamp} / {@link UpdateTimestamp}.
 *   <li>La relación {@code @OneToMany} con {@link OrderProductEntity} usa
 *       {@code cascade = ALL + orphanRemoval = true} para reflejar el ON DELETE CASCADE
 *       definido en la FK {@code fk_order_products_order}.
 * </ul>
 */
@Entity
@Table(
    name    = "orders",
    schema  = "public",
    uniqueConstraints = @UniqueConstraint(
        name        = "uq_orders_order_number",
        columnNames = "order_number"
    )
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", columnDefinition = "uuid", updatable = false, nullable = false)
    @EqualsAndHashCode.Include
    private UUID id;

    @Column(name = "order_number", length = 50, nullable = false, unique = true)
    private String orderNumber;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "customer_name", length = 200, nullable = false)
    private String customerName;

    @Column(name = "created_by", length = 100, nullable = false)
    private String createdBy;

    @Column(name = "order_date", nullable = false)
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Builder.Default
    private LocalDateTime orderDate = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    @ColumnDefault("'PENDING'")
    @Builder.Default
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "total_amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal totalAmount;

    @Column(name = "currency", length = 3, nullable = false)
    @ColumnDefault("'USD'")
    @Builder.Default
    private String currency = "USD";

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @OneToMany(
        mappedBy      = "order",
        cascade       = CascadeType.ALL,
        orphanRemoval = true,
        fetch         = FetchType.LAZY
    )
    @Builder.Default
    private List<OrderProductEntity> orderProducts = new ArrayList<>();

    public void addOrderProduct(OrderProductEntity orderProduct) {
        orderProducts.add(orderProduct);
        orderProduct.setOrder(this);
    }

    public void removeOrderProduct(OrderProductEntity orderProduct) {
        orderProducts.remove(orderProduct);
        orderProduct.setOrder(null);
    }
}
