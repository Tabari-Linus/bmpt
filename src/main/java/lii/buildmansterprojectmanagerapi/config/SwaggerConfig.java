package lii.buildmansterprojectmanagerapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI taskManagementOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("BuildMaster Project Manager")
                        .description("An Api to build the build manager task.")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("BuildMaster Inc.")));
    }
}