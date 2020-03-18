package com.faminto.votacaoalmocoapi.helper;

import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class DataHelperTest {

	@Test
	public void givenDia_whenGetPrimeiroDiaUtilDaSemana_thenReturnSegundaFeira() {
		LocalDate dia = LocalDate.of(2020, Month.MARCH, 20);
		LocalDate primeiroDiaUtilDaSemanaEsperado = LocalDate.of(2020, Month.MARCH, 16);
		LocalDate primeiroDiaUtilDaSemana = DataHelper.getPrimeiroDiaUtilDaSemana(dia);
		
		assertEquals(primeiroDiaUtilDaSemanaEsperado, primeiroDiaUtilDaSemana);
		assertEquals(primeiroDiaUtilDaSemanaEsperado.getDayOfWeek(), DayOfWeek.MONDAY);
	}
	
	@Test
	public void givenDomingo_whenGetPrimeiroDiaUtilDaSemana_thenReturnSegundaFeira() {
		LocalDate dia = LocalDate.of(2020, Month.MARCH, 15);
		LocalDate primeiroDiaUtilDaSemanaEsperado = LocalDate.of(2020, Month.MARCH, 16);
		LocalDate primeiroDiaUtilDaSemana = DataHelper.getPrimeiroDiaUtilDaSemana(dia);
		
		assertEquals(primeiroDiaUtilDaSemanaEsperado, primeiroDiaUtilDaSemana);
		assertEquals(primeiroDiaUtilDaSemanaEsperado.getDayOfWeek(), DayOfWeek.MONDAY);
	}
	
	@Test
	public void givenSegundaFeira_whenGetPrimeiroDiaUtilDaSemana_thenReturnMesmoDia() {
		LocalDate dia = LocalDate.of(2020, Month.MARCH, 16);
		LocalDate primeiroDiaUtilDaSemanaEsperado = LocalDate.of(2020, Month.MARCH, 16);
		LocalDate primeiroDiaUtilDaSemana = DataHelper.getPrimeiroDiaUtilDaSemana(dia);
		
		assertEquals(primeiroDiaUtilDaSemanaEsperado, primeiroDiaUtilDaSemana);
		assertEquals(primeiroDiaUtilDaSemanaEsperado.getDayOfWeek(), DayOfWeek.MONDAY);
	}
	
}
