package com.cursospring4.erp_lite.infrastructure.enums;

/**
 * Tipos de catálogo disponibles en el sistema.
 * Mapeado como String en MongoDB (campo "catalogType").
 */
public enum CatalogType {

    /** Categorías del catálogo de productos. */
    PRODUCT_CATEGORIES,

    /** Estados posibles de una orden. */
    ORDER_STATUSES,

    /** Métodos de pago disponibles. */
    PAYMENT_METHODS,
    SHIPPING_METHODS,
    COUNTRIES,
    CURRENCIES,

}
