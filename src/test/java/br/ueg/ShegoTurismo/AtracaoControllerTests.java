package br.ueg.ShegoTurismo;

import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.ueg.ShegoTurismo.controller.AtracaoController;
import br.ueg.ShegoTurismo.document.Atracao;
import br.ueg.ShegoTurismo.repository.AtracaoRepository;
import br.ueg.ShegoTurismo.services.AtracaoService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = AtracaoController.class)
@Import(AtracaoService.class)
public class AtracaoControllerTests {
	@MockBean
	private AtracaoRepository repository;
	@Autowired
	private WebTestClient webClient;
	
	
	@Test
	void testCreateEstabeleciemento() {
		Atracao atracao = new Atracao("1", "Circo de Solei");
		
		Mockito.when(repository.save(atracao))
		.thenReturn(Mono.just(atracao));
		
		webClient.post()
		.uri("/atracoes")
		.contentType(MediaType.APPLICATION_JSON)
		.body(BodyInserters.fromObject(atracao))
		.exchange()
		.expectStatus().isCreated();
		
		Mockito.verify(repository, times(1)).save(atracao);
	}
	
	@Test
	void getAllEstabelecimento() {
		List<Atracao> atracao = new ArrayList<Atracao>();
		atracao.add(new Atracao("1", "Circo de Solei"));
		atracao.add(new Atracao("1", "Expo Shego"));
		atracao.add(new Atracao("1", "Show Zeze"));
		
		Flux<Atracao> estabelecimentoFlux = Flux.fromIterable(atracao);
		
		Mockito.when(repository.findAll())
		.thenReturn(estabelecimentoFlux);
		
		webClient.get()
		.uri("/atracoes")
		.header(HttpHeaders.ACCEPT, "application/json")
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Atracao.class);
		
		Mockito.verify(repository, times(1)).findAll();
		
	}
	
	@Test
	void getIdEstabelecimento() {
		Atracao atracao = new Atracao("1", "Circo de Solei");
		
		Mockito.when(repository.findById(atracao.getId()))
		.thenReturn(Mono.just(atracao));
		
		webClient.get()
		.uri("/atracoes/{id}", atracao.getId())
		.exchange()
		.expectStatus().isOk()
		.expectBody()
		.jsonPath("$.id").isEqualTo(atracao.getId());
		
		Mockito.verify(repository, times(1)).findById(atracao.getId());
	}
	
	@Test
	void deleteIdEstabeleciemento() {
		Mono<Void> voidReturn = Mono.empty();
		
		Mockito.when(repository.deleteById("1"))
		.thenReturn(voidReturn);
		
		webClient.delete().uri("/atracoes/{id}", "1")
        .exchange()
        .expectStatus().isOk();
	}
}
