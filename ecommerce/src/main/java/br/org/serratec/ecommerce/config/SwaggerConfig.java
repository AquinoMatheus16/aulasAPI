package br.org.serratec.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("br.org.serratec.ecommerce")).paths(PathSelectors.any())
				.build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder().title("API de E-commerce").description(
				"Essa é uma API desenvolvida para o trabalho final da disciplina de Desenvolvimento de API Restful")
				.license("Apache License Version 2.0").licenseUrl("https://www.apache.org/license/LICENSE-2.0")
				.version("1.0.0").contact(new Contact("Serratec", "https://serratec.org/", "contato@serratec.org"))
				.build();
		return apiInfo;
	}
}
