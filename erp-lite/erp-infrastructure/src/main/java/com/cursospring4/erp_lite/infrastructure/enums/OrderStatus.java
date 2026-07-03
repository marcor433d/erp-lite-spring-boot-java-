package com.cursospring4.erp_lite.infrastructure.enums;

/**
 * Ciclo de vida de una orden.
 * Mapeado como STRING en BD (columna status VARCHAR(20), default 'PENDING').
 */
public enum OrderStatus {

    /** Creada, pendiente de confirmación. */
    PENDING,

    /** Confirmada por el sistema o el operador. */
    CONFIRMED,

    /** En preparación o procesamiento interno. */
    PROCESSING,

    /** Enviada al cliente. */
    SHIPPED,

    /** Entregada exitosamente. */
    DELIVERED,

    /** Cancelada antes de entrega. */
    CANCELLED
}
