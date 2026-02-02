package com.example.rest_api_assignment.config.mapping;

import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NamingConventions;
import org.modelmapper.spi.NamingConvention;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setSourceNamingConvention(NamingConventions.NONE)
                .setDestinationNamingConvention(NamingConventions.NONE)
                .setSkipNullEnabled(true)
                .setPropertyCondition(context -> {
                    // Skip uninitialized Hibernate lazy proxies and collections
                    return Hibernate.isInitialized(context.getSource());
                });
        return modelMapper;
    }
}
