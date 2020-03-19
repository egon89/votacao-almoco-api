package com.faminto.votacaoalmocoapi.helper;

import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.Locale;

public final class DataHelper {

	private static final long SEGUNDA_FEIRA = 2L;
	
	private DataHelper() {
		throw new UnsupportedOperationException();
	}
	
	public static LocalDate getPrimeiroDiaUtilDaSemana(LocalDate dia) {
		TemporalField field = WeekFields.of(Locale.getDefault()).dayOfWeek();
		
		return dia.with(field, SEGUNDA_FEIRA);
	}
	
	public static boolean isMaiorOuIgual(LocalDate d1, LocalDate d2) {
		return d1.isEqual(d2) || d1.isAfter(d2);
	}
	
	public static boolean isMenorOuIgual(LocalDate d1, LocalDate d2) {
		return d1.isEqual(d2) || d1.isBefore(d2);
	}
	
}
