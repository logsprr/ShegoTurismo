package br.ueg.ShegoTurismo.services;

import br.ueg.ShegoTurismo.document.Estabelecimento;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EstabelecimentoService {

	Flux<Estabelecimento> findAll();
	Mono<Estabelecimento> findById(String id);
	Mono<Estabelecimento> save(Estabelecimento estabelecimento);
	Mono<Void> deleteById(String id);
}
