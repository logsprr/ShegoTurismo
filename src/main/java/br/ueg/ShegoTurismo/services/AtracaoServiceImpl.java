package br.ueg.ShegoTurismo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.ShegoTurismo.document.Atracao;
import br.ueg.ShegoTurismo.repository.AtracaoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AtracaoServiceImpl  {

	@Autowired
	AtracaoRepository pr;

	public Flux<Atracao> findAll() {
		return pr.findAll();
	}

	public Mono<Atracao> findById(String id) {
		return pr.findById(id);
	}

	public Mono<Atracao> save(Atracao atracao) {
		return pr.save(atracao);
	}

	public Mono<Void> deleteById(String id) {
		return pr.deleteById(id);
	}
}
