package com.cursospring4.erp_lite.infrastructure.persistence.mongo.documents;

import com.mongodb.lang.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

/**
 * Configuración de Spring Data MongoDB.
 * Activa auditoría (@CreatedDate / @LastModifiedDate) y suprime el campo _class
 * que Spring Data MongoDB añade por defecto en cada documento.
 */
@Configuration
@EnableMongoAuditing
public class MongoConfig {

    @Bean
    public MappingMongoConverter mappingMongoConverter(
            @NonNull MongoDatabaseFactory factory,
            @NonNull MongoMappingContext context,
            @NonNull MongoCustomConversions conversions) {

        DbRefResolver resolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter converter = new MappingMongoConverter(resolver, context);
        converter.setCustomConversions(conversions);
        // null elimina el campo _class de todos los documentos
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }
}
