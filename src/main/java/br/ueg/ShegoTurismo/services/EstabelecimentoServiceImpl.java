package br.ueg.ShegoTurismo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ueg.ShegoTurismo.document.Estabelecimento;
import br.ueg.ShegoTurismo.repository.EstabelecimentoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EstabelecimentoServiceImpl   {

	@Autowired
	EstabelecimentoRepository pr;


	public Flux<Estabelecimento> findAll() {
		return pr.findAll();
	}


	public Mono<Estabelecimento> findById(String id) {
		return pr.findById(id);
	}

	public Mono<Estabelecimento> save(Estabelecimento estabelecimento) {
		return pr.save(estabelecimento);
	}

	public Mono<Void> deleteById(String id) {
		return pr.deleteById(id);
	}

}
