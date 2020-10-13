package br.ueg.ShegoTurismo.controller;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.ueg.ShegoTurismo.document.Atracao;
import br.ueg.ShegoTurismo.services.AtracaoServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@CrossOrigin(origins = "*")
@RestController
public class AtracaoController {
	@Autowired
	AtracaoServiceImpl service;
	
	@GetMapping(value="/atracoes")
	public Flux<Atracao> getAtracoes(){
		return service.findAll();
	}
	
	@GetMapping(value="/atracoes/{id}")
	public Mono<Atracao> getAtracoesId(@PathVariable String id){
		return service.findById(id);
	}
	
	@PostMapping(value="/atracoes")
	public Mono<Atracao> saveAtracao(@RequestBody Atracao atracao){
		return service.save(atracao);
	}
	@GetMapping(value="/atracoes/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	public Flux<Tuple2<Long, Atracao>> getAtracaoByWebflux(){

		Flux<Long> interval = Flux.interval(Duration.ofSeconds(5));
        Flux<Atracao> playlistFlux = service.findAll();
        
        return Flux.zip(interval, playlistFlux);
        
	}
	@DeleteMapping(value="/atracoes/{id}")
	public Mono<Void> deleteAtracoesId(@PathVariable String id){
		return service.deleteById(id);
	}
}
