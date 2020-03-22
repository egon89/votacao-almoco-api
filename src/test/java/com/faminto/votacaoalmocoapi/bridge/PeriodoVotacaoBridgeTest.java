package com.faminto.votacaoalmocoapi.bridge;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.configuration.VotacaoHoraLimite;
import com.faminto.votacaoalmocoapi.exception.BusinessException;

@ExtendWith(MockitoExtension.class)
public class PeriodoVotacaoBridgeTest {

	@InjectMocks
	private PeriodoVotacaoBridge bridge;
	
	@Mock
	private VotacaoHoraLimite votacaoHoraLimite;
	
	public void setUp() {
	    when(votacaoHoraLimite.getHoraLimite()).thenReturn(LocalTime.of(11, 30));
	}
	
	@Test
	public void givenDiaAndMomentoDoVotoAntesDoHorarioLimite_whenIsPeriodoValido_thenReturnTrue() {
		setUp();
		LocalDate diaVotacao = LocalDate.of(2020, Month.MARCH, 18);
		LocalDateTime momentoVoto = LocalDateTime.of(2020, Month.MARCH, 18, 11, 25);
		
		assertTrue(bridge.isPeriodoValido(diaVotacao, momentoVoto));
	}
	
	@Test
	public void givenDiaAndMomentoDoVotoAntesDoHorarioLimite_whenValidaPeriodoValido_thenNaoDeveLancarExcecao() {
		setUp();
		LocalDate diaVotacao = LocalDate.of(2020, Month.MARCH, 18);
		LocalDateTime momentoVoto = LocalDateTime.of(2020, Month.MARCH, 18, 11, 25);
		
		assertDoesNotThrow(() -> bridge.validaPeriodoValido(diaVotacao, momentoVoto));
	}
	
	@Test
	public void givenDiaAndMomentoDoVotoAntesDoHorarioLimite_whenValidaPeriodoValido_thenDeveLancarBusinessException() {
		when(votacaoHoraLimite.getHoraLimite()).thenReturn(LocalTime.of(11, 25));
		LocalDate diaVotacao = LocalDate.of(2020, Month.MARCH, 18);
		LocalDateTime momentoVoto = LocalDateTime.of(2020, Month.MARCH, 18, 11, 30);
		
		assertThrows(BusinessException.class, () -> bridge.validaPeriodoValido(diaVotacao, momentoVoto));
	}
	
}
