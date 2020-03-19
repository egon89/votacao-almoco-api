package com.faminto.votacaoalmocoapi.bridge;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.mock.EleicaoMocker;
import com.faminto.votacaoalmocoapi.mock.RestauranteMocker;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Restaurante;

@ExtendWith(MockitoExtension.class)
public class RestauranteEleitoSemanalBridgeTest {

	@InjectMocks
	private RestauranteEleitoSemanaBridge bridge;
	
	@Test
	public void givenRestauranteAndEleicaoList_whenPodeEleger_thenReturnTrueParaPrimeiraEleicaoDaSemana() {
		LocalDate diaEleicao = LocalDate.of(2020, Month.MARCH, 18);
		Restaurante restauranteEleito = RestauranteMocker.ENTIDADE_BUILDER.id(1L).build();
		List<Eleicao> eleicoes = EleicaoMocker.buildDefaultEleicoes(LocalDate.of(2020, Month.MARCH, 15), LocalDate.of(2020, Month.MARCH, 25));
		
		assertTrue(bridge.podeEleger(restauranteEleito, diaEleicao, eleicoes));
	}
	
	
	
}
