package com.ChefBookingSystem.demo;

import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

public class SpringFoxCoonfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.ChefBookingSystem.demo")).build();
    }

    private ApiInfo apiDetails() {
        return new ApiInfo("Online Chef Booking Application", "Api for chef booking application", "1.0", "Free to use",
                new springfox.documentation.service.Contact(null, null, null), "Github repo link",
                "https://github.com/utsav4504", Collections.emptyList());

    }
}
