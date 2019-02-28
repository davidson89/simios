package br.com.mercadolivre.simios.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2)
			.apiInfo(this.getApitInfo())
			.groupName("dna")
			.select()
			.apis(RequestHandlerSelectors.basePackage("br.com.mercadolivre.simios.infrastructure.controller"))
			.paths(PathSelectors.any())
			.build();
    }

    private ApiInfo getApitInfo() {
	return new ApiInfoBuilder().title("Validador de DNA")
			.description("Essa aplicação é responsável por validar se um DNA é de um Simio (mutante) ou de um humano.")
			.build();
    }

}
