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
	
}
