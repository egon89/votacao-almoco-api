package com.faminto.votacaoalmocoapi.bridge;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.mock.UsuarioMocker;
import com.faminto.votacaoalmocoapi.mock.VotacaoMocker;
import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.model.Votacao;

@ExtendWith(MockitoExtension.class)
public class VotacaoDiariaBridgeTest {

	@InjectMocks
	private VotacaoDiariaBridge bridge;
	
	@Mock
	private PeriodoVotacaoBridge periodoVotacaoBridge;
	
	public void setUp() {
		doNothing().when(periodoVotacaoBridge).validaPeriodoValido(any(LocalDate.class), any(LocalDateTime.class));
	}
	
	@Test
	public void givenDiaUsuarioVotacoes_whenPodeVotar_thenLancaBusinessExceptionParaVotoDuplicadoDoMesmoUsuarioNoDia() {
		setUp();
		LocalDate dia = LocalDate.of(2020, Month.MARCH, 1);
		Usuario usuario = UsuarioMocker.ENTIDADE;
		Votacao votacaoUm = VotacaoMocker.ENTIDADE;
		Votacao votacaoDois = VotacaoMocker.ENTIDADE_DOIS;
		
		assertThrows(BusinessException.class, () -> bridge.podeVotar(dia, usuario, VotacaoMocker.buildVotacaoList(votacaoUm, votacaoDois)));
	}
	
	@Test
	public void givenDiaUsuarioVotacoes_whenPodeVotar_thenNaoLancaExcecaoParaPrimeiroVotoDoMesmoUsuarioNoDia() {
		setUp();
		LocalDate dia = LocalDate.of(2020, Month.MARCH, 2);
		Usuario usuario = UsuarioMocker.ENTIDADE;
		Votacao votacaoUm = VotacaoMocker.ENTIDADE;
		Votacao votacaoDois = VotacaoMocker.ENTIDADE_DOIS;
		
		assertDoesNotThrow(() -> bridge.podeVotar(dia, usuario, VotacaoMocker.buildVotacaoList(votacaoUm, votacaoDois)));
	}
	
	@Test
	public void givenUsuarioNuloAndVotacoesNulo_whenPodeVotar_thenThrowsIllegalArgumentException() {
		LocalDate dia = LocalDate.of(2020, Month.MARCH, 2);
		
		assertThrows(IllegalArgumentException.class, () -> bridge.podeVotar(dia, null, null));
	}
	
}
