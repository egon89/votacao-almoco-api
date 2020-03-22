package com.faminto.votacaoalmocoapi.bridge;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.configuration.VotacaoHoraLimite;
import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;

@Component
public class PeriodoVotacaoBridge {

	private VotacaoHoraLimite votacaoHoraLimite;
	
	@Autowired
	public PeriodoVotacaoBridge(VotacaoHoraLimite votacaoHoraLimite) {
		this.votacaoHoraLimite = votacaoHoraLimite;
	}

	public boolean isPeriodoValido(LocalDate diaVotacao, LocalDateTime momentoVoto) {
		return momentoVoto.toLocalDate().isEqual(diaVotacao) && !momentoVoto.toLocalTime().isAfter(votacaoHoraLimite.getHoraLimite());
	}
	
	public void validaPeriodoValido(LocalDate diaVotacao, LocalDateTime momentoVoto) {
		if (!isPeriodoValido(diaVotacao, momentoVoto)) {
			throw new BusinessException(MessageKey.VOTO_PERIODO_INVALIDO);
		}
	}
	
}
