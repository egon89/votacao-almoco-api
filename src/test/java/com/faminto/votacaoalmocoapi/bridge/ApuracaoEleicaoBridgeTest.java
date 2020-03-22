package com.faminto.votacaoalmocoapi.bridge;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.mock.RestauranteMocker;
import com.faminto.votacaoalmocoapi.mock.VotacaoMocker;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Votacao;
import com.faminto.votacaoalmocoapi.repository.VotacaoRepository;

@ExtendWith(MockitoExtension.class)
public class ApuracaoEleicaoBridgeTest {

	@InjectMocks
	private ApuracaoEleicaoBridge bridge;
	
	@Mock
	private VotacaoRepository votacaoRepository;
	
	@Test
	public void givenDiaNuloOrVotacoesNulo_whenApurar_thenThrowsIllegalArgumentException() {
		assertThrows(IllegalArgumentException.class, () -> bridge.apurar(null));
	}
	
	@Test
	public void givenDiaAndVotacaoList_whenApurar_ThenReturnEleicaoVencedora() {
		LocalDate diaVotacao = LocalDate.of(2020, Month.MARCH, 18);
		List<Votacao> votacoes = VotacaoMocker.buildVotacoesDoDia(diaVotacao);
		when(votacaoRepository.findAll()).thenReturn(votacoes);
		
		Optional<Eleicao> eleicaoVencedoraOpt = bridge.apurar(diaVotacao);
		
		assertTrue(eleicaoVencedoraOpt.isPresent());
		assertEquals(3L, eleicaoVencedoraOpt.get().getVotos());
		assertEquals(RestauranteMocker.ENTIDADE, eleicaoVencedoraOpt.get().getRestaurante());
	}
	
	@Test
	public void givenDiaAndVotacaoList_whenApurar_ThenDeveConsiderarSomenteVotoDoDia() {
		LocalDate diaVotacao = LocalDate.of(2020, Month.MARCH, 18);
		List<Votacao> votacoes = VotacaoMocker.buildVotacoesComDiasDiferentes(diaVotacao);
		when(votacaoRepository.findAll()).thenReturn(votacoes);
		
		Optional<Eleicao> eleicaoVencedoraOpt = bridge.apurar(diaVotacao);
		
		assertTrue(eleicaoVencedoraOpt.isPresent());
		assertEquals(1L, eleicaoVencedoraOpt.get().getVotos());
	}
	
	@Test
	public void givenDiaAndVotacaoList_whenApurar_ThenLancaBusinessExceptionParaEleicaoComEmpate() {
		LocalDate diaVotacao = LocalDate.of(2020, Month.MARCH, 18);
		List<Votacao> votacoes = VotacaoMocker.buildVotacoesDoDiaComEmpate(diaVotacao);
		when(votacaoRepository.findAll()).thenReturn(votacoes);
		
		assertThrows(BusinessException.class, () -> bridge.apurar(diaVotacao));
	}
	
}
