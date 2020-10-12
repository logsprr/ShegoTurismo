package br.ueg.ShegoTurismo;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.HandlerFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

public class SwaggerUiHandler implements HandlerFunction<ServerResponse> {
	@Override
	public Mono<ServerResponse> handle(ServerRequest request) {
		return ServerResponse.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON)
			.body(BodyInserters.fromObject(UiConfigurationBuilder.builder().build()));
	}
}
