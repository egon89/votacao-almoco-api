package com.faminto.votacaoalmocoapi.bridge;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.mock.EleicaoMocker;
import com.faminto.votacaoalmocoapi.mock.RestauranteMocker;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Restaurante;

@ExtendWith(MockitoExtension.class)
public class RestauranteEleitoSemanalBridgeTest {

	@InjectMocks
	private RestauranteEleitoSemanaBridge bridge;
	
	@Test
	public void givenRestauranteAndEleicaoList_whenPodeEleger_thenNaoLancaExcecaoParaPrimeiraEleicaoDaSemana() {
		LocalDate diaEleicao = LocalDate.of(2020, Month.MARCH, 18);
		Restaurante restauranteEleito = RestauranteMocker.ENTIDADE_BUILDER.id(1L).build();
		List<Eleicao> eleicoes = EleicaoMocker.buildDefaultEleicoes(LocalDate.of(2020, Month.MARCH, 15), LocalDate.of(2020, Month.MARCH, 25));
		
		assertDoesNotThrow(() -> bridge.podeEleger(restauranteEleito, diaEleicao, eleicoes));
	}
	
	@Test
	public void givenRestauranteAndEleicaoList_whenPodeEleger_thenLancaBusinessExceptionParaRestauranteJaEleitoNaSemana() {
		LocalDate diaEleicao = LocalDate.of(2020, Month.MARCH, 18);
		Restaurante restauranteEleito = RestauranteMocker.ENTIDADE_BUILDER.id(3L).build();
		List<Eleicao> eleicoes = EleicaoMocker.buildDefaultEleicoes(LocalDate.of(2020, Month.MARCH, 15), LocalDate.of(2020, Month.MARCH, 25));
		
		assertThrows(BusinessException.class, () -> bridge.podeEleger(restauranteEleito, diaEleicao, eleicoes));
	}
	
}
