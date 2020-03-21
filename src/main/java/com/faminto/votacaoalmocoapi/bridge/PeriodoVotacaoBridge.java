package com.faminto.votacaoalmocoapi.bridge;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;

@Component
public class PeriodoVotacaoBridge {

	
//	@Value("#{T(java.time.LocalTime).parse('${votacao.horaLimite}', T(java.time.format.DateTimeFormatter).ofPattern('HH:mm'))}")
	@Value("${votacao.horaLimite}")
	public LocalTime horaLimite;

	public boolean isPeriodoValido(LocalDate diaVotacao, LocalDateTime momentoVoto) {
		return momentoVoto.toLocalDate().isEqual(diaVotacao) && !momentoVoto.toLocalTime().isAfter(horaLimite);
	}
	
	public void validaPeriodoValido(LocalDate diaVotacao, LocalDateTime momentoVoto) {
		System.out.println(horaLimite);
		if (!isPeriodoValido(diaVotacao, momentoVoto)) {
			throw new BusinessException(MessageKey.VOTO_PERIODO_INVALIDO);
		}
		//TODO pegar pela property
	}
	
}
