package eco.com.spring.mcsv.feddex.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

/**
 * @author edisoncsi on 20/9/23
 * @project mcsv-feddex
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("eco.com.spring.mcsv.feddex.controllers"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo("My REST API",
                "Some custom description of API.",
                "API TOS",
                "Terms of service",
                new Contact("Edison Collaguazo", "www.example.com", "edisoncsi@company.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }
}
