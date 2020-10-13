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

import br.ueg.ShegoTurismo.controller.EstabelecimentoController;
import br.ueg.ShegoTurismo.document.Estabelecimento;
import br.ueg.ShegoTurismo.repository.EstabelecimentoRepository;
import br.ueg.ShegoTurismo.services.EstabelecimentoServiceImpl;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = EstabelecimentoController.class)
@Import(EstabelecimentoServiceImpl.class)
public class EstabelecimentoControllerTests {

	@MockBean
	private EstabelecimentoRepository repository;
	@Autowired
	private WebTestClient webClient;

	@Test
	void testCreateEstabeleciemento() {
		Estabelecimento estabeleciemento = new Estabelecimento("1", "Lojas Americanas");

		Mockito.when(repository.save(estabeleciemento)).thenReturn(Mono.just(estabeleciemento));

		webClient.post().uri("/estabelecimentos").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.body(Mono.just(estabeleciemento), Estabelecimento.class).exchange().expectStatus().isOk();
	}

	@Test
	void getAllEstabelecimento() {
		List<Estabelecimento> estabeleciemento = new ArrayList<Estabelecimento>();
		estabeleciemento.add(new Estabelecimento("1", "Lojas Americanas"));
		estabeleciemento.add(new Estabelecimento("1", "Magazine Luiza"));
		estabeleciemento.add(new Estabelecimento("1", "Carrefour"));

		Flux<Estabelecimento> estabelecimentoFlux = Flux.fromIterable(estabeleciemento);

		Mockito.when(repository.findAll()).thenReturn(estabelecimentoFlux);

		webClient.get().uri("/estabelecimentos").header(HttpHeaders.ACCEPT, "application/json").exchange()
				.expectStatus().isOk().expectBodyList(Estabelecimento.class);

		Mockito.verify(repository, times(1)).findAll();

	}

	@Test
	void getIdEstabelecimento() {
		Estabelecimento estabeleciemento = new Estabelecimento("1", "Lojas Americanas");

		Mockito.when(repository.findById(estabeleciemento.getId())).thenReturn(Mono.just(estabeleciemento));

		webClient.get().uri("/estabelecimentos/{id}", estabeleciemento.getId()).exchange().expectStatus().isOk()
				.expectBody().jsonPath("$.id").isEqualTo(estabeleciemento.getId());

		Mockito.verify(repository, times(1)).findById(estabeleciemento.getId());
	}

	@Test
	void deleteIdEstabeleciemento() {
		Mono<Void> voidReturn = Mono.empty();

		Mockito.when(repository.deleteById("1")).thenReturn(voidReturn);

		webClient.delete().uri("/estabelecimentos/{id}", "1").exchange().expectStatus().isOk();
	}

}
