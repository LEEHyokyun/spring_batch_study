package com.kakao.project.common.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;



public class SwaggerConfig {
	@Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(apiInfo());
    }

    private io.swagger.v3.oas.models.info.Info apiInfo() {
        return new Info()
                .title("customer Watch List") // API의 제목
                .description("API Description") // API에 대한 설명
                .version("1.0.0"); // API의 버전
    }
}
