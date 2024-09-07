package com.stockgro.backend.config;

import com.stockgro.backend.model.enums.AssignmentType;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

@Data
@Validated
@Configuration
@ConfigurationProperties("parking-lot-config")
public class CapacityConfig {

    @NotNull
    private Integer capacity;

    @NotNull
    private AssignmentType type;

}
