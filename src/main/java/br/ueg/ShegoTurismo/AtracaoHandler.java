package br.ueg.ShegoTurismo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import br.ueg.ShegoTurismo.document.Atracao;
import br.ueg.ShegoTurismo.services.AtracaoService;
import reactor.core.publisher.Mono;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;
public class AtracaoHandler {

	@Autowired
	AtracaoService service;
	
	public Mono<ServerResponse> findAll(ServerRequest request){
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findAll(), Atracao.class);
	}
	public Mono<ServerResponse> findById(ServerRequest request){
		String id = request.pathVariable("id");
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(service.findById(id), Atracao.class);
	}
	public Mono<ServerResponse> save(ServerRequest request){
		final Mono<Atracao> playlist = request.bodyToMono(Atracao.class);
		return ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(fromPublisher(playlist.flatMap(service::save), Atracao.class));
	}
	public Mono<ServerResponse> deleteById(ServerRequest request){
		String id = request.pathVariable("id");
		return ok()
				.build(service.deleteById(id));
	}
	
}
