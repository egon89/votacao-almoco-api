package com.faminto.votacaoalmocoapi.bridge;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.mock.UsuarioMocker;
import com.faminto.votacaoalmocoapi.mock.VotacaoMocker;
import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.model.Votacao;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest // com contexto
public class VotacaoDiariaBridgeTest {

	@InjectMocks
	private VotacaoDiariaBridge bridge;
	
	@Test
	public void givenDiaUsuarioVotacoes_whenPodeVotar_thenReturnFalseParaVotoDuplicadoDoMesmoUsuarioNoDia() {
		LocalDate dia = LocalDate.of(2020, Month.MARCH, 1);
		Usuario usuario = UsuarioMocker.ENTIDADE;
		Votacao votacaoUm = VotacaoMocker.ENTIDADE;
		Votacao votacaoDois = VotacaoMocker.ENTIDADE_DOIS;
		
		assertEquals(bridge.podeVotar(dia, usuario, VotacaoMocker.buildVotacaoList(votacaoUm, votacaoDois)), Boolean.FALSE);
	}
	
	@Test
	public void givenDiaUsuarioVotacoes_whenPodeVotar_thenReturnTrueParaPrimeiroVotoDoMesmoUsuarioNoDia() {
		LocalDate dia = LocalDate.of(2020, Month.MARCH, 2);
		Usuario usuario = UsuarioMocker.ENTIDADE;
		Votacao votacaoUm = VotacaoMocker.ENTIDADE;
		Votacao votacaoDois = VotacaoMocker.ENTIDADE_DOIS;
		
		assertEquals(bridge.podeVotar(dia, usuario, VotacaoMocker.buildVotacaoList(votacaoUm, votacaoDois)), Boolean.TRUE);
	}
	
	@Test
	public void givenUsuarioNuloAndVotacoesNulo_whenPodeVotar_thenReturnTrueParaPrimeiroVotoDoMesmoUsuarioNoDia() {
		LocalDate dia = LocalDate.of(2020, Month.MARCH, 2);
		
		assertThrows(IllegalArgumentException.class, () -> bridge.podeVotar(dia, null, null));
	}
	
}
