package br.ueg.ShegoTurismo;

import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

public class EstabelecimentoRouter {
	public RouterFunction<ServerResponse> route(EstabelecimentoHandler handler){
		return RouterFunctions
				.route(GET("/estabelecimentos").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
				.andRoute(GET("/estabelecimentos/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
				.andRoute(POST("/estabelecimentos").and(accept(MediaType.APPLICATION_JSON)), handler::save)
				.andRoute(DELETE("/estabelecimentos/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteById);
	}
}
