package br.ueg.ShegoTurismo;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.ueg.ShegoTurismo.document.Estabelecimento;
import br.ueg.ShegoTurismo.services.EstabelecimentoService;
import reactor.core.publisher.Mono;

public class EstabelecimentoHandler {
	@Autowired
	EstabelecimentoService service;
	
	public Mono<ServerResponse> findAll(ServerRequest request){
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAll(), Estabelecimento.class);
	}
	public Mono<ServerResponse> findById(ServerRequest request){
		String id = request.pathVariable("id");
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findById(id), Estabelecimento.class);
	}
	public Mono<ServerResponse> save(ServerRequest request){
		final Mono<Estabelecimento> playlist = request.bodyToMono(Estabelecimento.class);
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromPublisher(playlist.flatMap(service::save), Estabelecimento.class));
	}
	public Mono<ServerResponse> deleteById(ServerRequest request){
		String id = request.pathVariable("id");
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.deleteById(id), Estabelecimento.class);
	}
}
