package com.bowwowcare.sm.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger2Config {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/api/**")
                .build();
    }
    @Bean
    public OpenAPI openAPI() {

        String jwtSchemeName = "X-AUTH-TOKEN";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        Components components = new Components().addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                .name(jwtSchemeName)
                .in(SecurityScheme.In.HEADER)
                .type(SecurityScheme.Type.APIKEY));

        return new OpenAPI()
                .info(new Info().title("BowwowCare API")
                        .description("BowwowCare 프로젝트 API 명세서")
                        .version("v0.0.1"))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}
