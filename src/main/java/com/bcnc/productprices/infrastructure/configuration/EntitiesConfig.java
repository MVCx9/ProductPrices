package com.bcnc.productprices.infrastructure.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = "com.bcnc.productprices.domain.model;\n")
public class EntitiesConfig {

    EntitiesConfig() {}

}
