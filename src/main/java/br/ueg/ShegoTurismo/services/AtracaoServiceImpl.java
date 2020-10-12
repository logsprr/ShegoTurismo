package br.ueg.ShegoTurismo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.ShegoTurismo.document.Atracao;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AtracaoServiceImpl implements AtracaoService {

	@Autowired
	AtracaoService pr;

	@Override
	public Flux<Atracao> findAll() {
		return pr.findAll();
	}

	@Override
	public Mono<Atracao> findById(String id) {
		return pr.findById(id);
	}

	@Override
	public Mono<Atracao> save(Atracao atracao) {
		return pr.save(atracao);
	}

	public Mono<Void> deleteById(String id) {
		return pr.deleteById(id);
	}
}
