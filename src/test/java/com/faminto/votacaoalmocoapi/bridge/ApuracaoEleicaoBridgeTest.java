package com.faminto.votacaoalmocoapi.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.mock.RestauranteMocker;
import com.faminto.votacaoalmocoapi.mock.VotacaoMocker;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Votacao;

@ExtendWith(MockitoExtension.class)
public class ApuracaoEleicaoBridgeTest {

	@InjectMocks
	private ApuracaoEleicaoBridge bridge;
	
	@Test
	public void givenDiaNuloOrVotacoesNulo_whenApurar_thenThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> bridge.apurar(null, null));
	}
	
	@Test
	public void givenDiaAndVotacaoList_whenApurar_ThenReturnEleicaoVencedora() {
		LocalDate diaVotacao = LocalDate.of(2020, Month.MARCH, 18);
		List<Votacao> votacoes = VotacaoMocker.buildVotacoesDoDia(diaVotacao);
		Optional<Eleicao> eleicaoVencedoraOpt = bridge.apurar(diaVotacao, votacoes);
		
		assertTrue(eleicaoVencedoraOpt.isPresent());
		assertEquals(3L, eleicaoVencedoraOpt.get().getVotos());
		assertEquals(RestauranteMocker.ENTIDADE, eleicaoVencedoraOpt.get().getRestaurante());
	}
	
	@Test
	public void givenDiaAndVotacaoList_whenApurar_ThenDeveConsiderarSomenteVotoDoDia() {
		LocalDate diaVotacao = LocalDate.of(2020, Month.MARCH, 18);
		List<Votacao> votacoes = VotacaoMocker.buildVotacoesComDiasDiferentes(diaVotacao);
		Optional<Eleicao> eleicaoVencedoraOpt = bridge.apurar(diaVotacao, votacoes);
		
		assertTrue(eleicaoVencedoraOpt.isPresent());
		assertEquals(1L, eleicaoVencedoraOpt.get().getVotos());
	}
	
	@Test
	public void givenDiaAndVotacaoList_whenApurar_ThenLancaBusinessExceptionParaEleicaoComEmpate() {
		LocalDate diaVotacao = LocalDate.of(2020, Month.MARCH, 18);
		List<Votacao> votacoes = VotacaoMocker.buildVotacoesDoDiaComEmpate(diaVotacao);
		
		assertThrows(BusinessException.class, () -> bridge.apurar(diaVotacao,votacoes));
	}
	
}
