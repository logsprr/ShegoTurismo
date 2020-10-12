package br.ueg.ShegoTurismo;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
public class AtracaoRouter {

	public RouterFunction<ServerResponse> route(AtracaoHandler handler){
		return RouterFunctions
				.route(GET("/atracoes").and(accept(MediaType.APPLICATION_JSON)), handler::findAll)
				.andRoute(GET("/atracoes/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::findById)
				.andRoute(POST("/atracoes").and(accept(MediaType.APPLICATION_JSON)), handler::save)
				.andRoute(DELETE("/atracoes/{id}").and(accept(MediaType.APPLICATION_JSON)), handler::deleteById);
	}
}
