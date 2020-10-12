package br.ueg.ShegoTurismo.services;

import br.ueg.ShegoTurismo.document.Atracao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AtracaoService {
	Flux<Atracao> findAll();
	Mono<Atracao> findById(String id);
	Mono<Atracao> save(Atracao atracao);
	Mono<Void> deleteById(String id);
}

