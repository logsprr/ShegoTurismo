package br.ueg.ShegoTurismo.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.ShegoTurismo.document.Estabelecimento;
import br.ueg.ShegoTurismo.services.EstabelecimentoService;
import io.swagger.annotations.ApiOperation;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@CrossOrigin(origins = "*")
@RestController
public class EstabelecimentoController {

	@Autowired
	EstabelecimentoService service;
	
	@GetMapping(value="/estabelecimentos")
	@ApiOperation(value="Listar os estabelecimentos")
	public Flux<Estabelecimento> getEstabelecimentos(){
		return service.findAll();
	}
	
	@GetMapping(value="/estabelecimentos/{id}")
	@ApiOperation(value="Listar os estabelecimentos por id")
	public Mono<Estabelecimento> getEstabelecimentosId(@PathVariable String id){
		return service.findById(id);
	}
	
	@PostMapping(value="/estabelecimentos")
	@ApiOperation(value="Salvar um  estabelecimento")
	public Mono<Estabelecimento> saveEstabelecimento(@RequestBody Estabelecimento estabelecimento){
		return service.save(estabelecimento);
	}
	@GetMapping(value="/estabelecimentos/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ApiOperation(value="Listar os estabelecimentos por tempo")
	public Flux<Tuple2<Long, Estabelecimento>> getEstabelecimentoByWebflux(){

		Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
        Flux<Estabelecimento> playlistFlux = service.findAll();

        return Flux.zip(interval, playlistFlux);
        
	}
}
