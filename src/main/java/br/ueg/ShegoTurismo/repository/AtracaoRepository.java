package br.ueg.ShegoTurismo.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.ueg.ShegoTurismo.document.Atracao;

public interface AtracaoRepository extends ReactiveMongoRepository<Atracao, String> {

}
