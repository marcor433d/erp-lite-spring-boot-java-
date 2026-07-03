package com.cursospring4.erp_lite.infrastructure.persistence.mongo.documents;

import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Objeto embebido que representa un ítem dentro de un catálogo.
 * Se mapea como documento anidado dentro del array {@code items} de {@link CatalogDocument}.
 *
 * <p>El campo {@code metadata} es flexible ({@code Map<String, Object>})
 * porque su estructura varía según el tipo de catálogo.
 */
public record CatalogItem(

        @Field("id")
        String id,

        @Field("code")
        String code,

        @Field("value")
        String value,

        @Field("description")
        String description,

        @Field("displayOrder")
        int displayOrder,

        @Field("metadata")
        Map<String, Object> metadata

) {

    public String icon() {
        if (metadata == null) return null;
        Object raw = metadata.get("icon");
        return raw != null ? raw.toString() : null;
    }

    public String color() {
        if (metadata == null) return null;
        Object raw = metadata.get("color");
        return raw != null ? raw.toString() : null;
    }

    public BigDecimal fee() {
        if (metadata == null) return null;
        Object raw = metadata.get("fee");
        if (raw == null)                  return null;
        if (raw instanceof BigDecimal bd) return bd;
        if (raw instanceof Number n)      return BigDecimal.valueOf(n.doubleValue());
        return new BigDecimal(raw.toString());
    }

    @SuppressWarnings("unchecked")
    public List<String> nextStatuses() {
        if (metadata == null) return null;
        Object raw = metadata.get("nextStatuses");
        return raw instanceof List<?> list ? (List<String>) list : null;
    }
}
