package com.lianglong.accountservice.config;

import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lianglong
 * @date 19-7-24
 */

@Configuration
public class MappingConverterAdapter {

    @Value("${spring.jackson.date-format:yyyy-MM-dd}")
    private String pattern;

    @Value("${spring.jackson.datetime-format:yyyy-MM-dd HH:mm:ss}")
    private String timePattern;

    /**
     * 返回LocalDate值（去掉LocalDate中的T）
     */

    public LocalDateSerializer localDateserializer() {
        return new LocalDateSerializer(DateTimeFormatter.ofPattern(pattern));
    }

    public LocalDateTimeSerializer localDateTimeSerializer() {
        return new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(timePattern));
    }

    //反序列化器
    public LocalDateTimeDeserializer localDateTimeDeserializer() {

        return new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(timePattern));
    }

    public LocalDateDeserializer localDateDeserializer() {
        return new LocalDateDeserializer(DateTimeFormatter.ofPattern(pattern));
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {

        Map<Class<?>, JsonSerializer<?>> classJsonSerializerHashMap = new HashMap<>();
        Map<Class<?>, JsonDeserializer<?>> classJsonDeserializerHashMap = new HashMap<>();
        classJsonSerializerHashMap.put(LocalDate.class, localDateserializer());
        classJsonSerializerHashMap.put(LocalDateTime.class, localDateTimeSerializer());
        classJsonDeserializerHashMap.put(LocalDate.class, localDateDeserializer());
        classJsonDeserializerHashMap.put(LocalDateTime.class, localDateTimeDeserializer());
        return builder ->
                builder.serializersByType(classJsonSerializerHashMap)
                        .deserializersByType(classJsonDeserializerHashMap);
    }
}
