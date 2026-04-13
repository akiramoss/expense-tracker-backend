package com.akiramoss.expense_tracker.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//http://localhost:8080/swagger-ui/index.html
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI expenseTrackerAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Expense Tracker API")
                        .version("1.0")
                        .description("API for managing personal expenses with Spring Boot"));
    }
}