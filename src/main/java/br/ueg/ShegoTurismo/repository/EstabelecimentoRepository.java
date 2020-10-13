package br.ueg.ShegoTurismo.repository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import br.ueg.ShegoTurismo.document.Estabelecimento;
public interface EstabelecimentoRepository extends ReactiveMongoRepository<Estabelecimento, String>{

}
