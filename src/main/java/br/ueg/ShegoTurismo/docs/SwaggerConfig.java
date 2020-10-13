package br.ueg.ShegoTurismo.docs;

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
	public Docket apiDoc() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(metaData()).enable(true).select()
				.paths(PathSelectors.any()).build();

	}

	// uri -> /swagger-ui/index.html
	private ApiInfo metaData() {
		return new ApiInfoBuilder().title("Projeto Shego Turismo").description("Programação III").version("0.0.1")
				.contact(new Contact("Gabriel Gomes", "ueg.br/si/gabriel", "gabriel@ueg.br"))
				.license("Apache License version 2.0").licenseUrl("http://www.apache.org/licenses/LICENSE-2.0").build();
	}
}
