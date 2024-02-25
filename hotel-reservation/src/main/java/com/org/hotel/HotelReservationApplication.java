package com.org.hotel;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EntityScan(basePackages = "com.org.hotel")
@OpenAPIDefinition(info = @Info(title = "Hotel API", version = "1.0", description = "Documentation for Hotel Reservation API"))
public class HotelReservationApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelReservationApplication.class, args);
    }

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder().group("public").pathsToMatch("/hotel/**").build();
    }
}
