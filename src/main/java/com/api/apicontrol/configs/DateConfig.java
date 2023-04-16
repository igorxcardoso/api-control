package com.api.apicontrol.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.format.DateTimeFormatter;

// Com a notação Configuration o Sprint vai levar em conta o que tem nessa classe ao iniciar a aplicação
@Configuration
public class DateConfig {
    //    Formatação global para data

    public static final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";
    public static LocalDateTimeSerializer LOCAL_DATETIME_SERIALIZER = new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DATETIME_FORMAT));

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        JavaTimeModule jtm = new JavaTimeModule();
        jtm.addSerializer(LOCAL_DATETIME_SERIALIZER);

        return new ObjectMapper().registerModule(jtm);
    }
}
