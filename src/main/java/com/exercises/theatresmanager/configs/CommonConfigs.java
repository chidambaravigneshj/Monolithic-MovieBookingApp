package com.exercises.theatresmanager.configs;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonConfigs {

  @Bean
  public ObjectMapper getObjectMapper(){
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, false);
    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return objectMapper;
  }

}
