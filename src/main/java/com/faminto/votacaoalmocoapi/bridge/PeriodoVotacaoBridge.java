package com.faminto.votacaoalmocoapi.bridge;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;

@Component
public class PeriodoVotacaoBridge {

	public static final LocalTime VOTACAO_HORARIO_LIMITE = LocalTime.of(11, 25);

	public boolean isPeriodoValido(LocalDate diaVotacao, LocalDateTime momentoVoto) {
		return momentoVoto.toLocalDate().isEqual(diaVotacao) && !momentoVoto.toLocalTime().isAfter(VOTACAO_HORARIO_LIMITE);
	}
	
	public void validaPeriodoValido(LocalDate diaVotacao, LocalDateTime momentoVoto) {
		if (!isPeriodoValido(diaVotacao, momentoVoto)) {
			throw new BusinessException(MessageKey.VOTO_PERIODO_INVALIDO);
		}
		//TODO pegar pela property
	}
	
}
