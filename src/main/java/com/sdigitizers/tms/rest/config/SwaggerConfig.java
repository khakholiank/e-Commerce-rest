package com.sdigitizers.tms.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Shriram Prajapat
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sdigitizers.tms.rest"))
                //.paths(regex("/user.*"))
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "ToursApp",
                "REST APIs for GayatriCabs",
                "1.0",
                "Terms of service",
                new Contact("Sanatan Digitizers Pvt Ltd", "https://sanatandigitizers.com", "solution.ramsofts@gmail.com"),
                "Gayatri Travel Services Pvt Ltd",
                "https://www.toursapp.in");
        
        return apiInfo;
    }

}
