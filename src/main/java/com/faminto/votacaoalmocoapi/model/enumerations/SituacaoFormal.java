package com.faminto.votacaoalmocoapi.model.enumerations;

import java.util.Random;

import lombok.Getter;

@Getter
public enum SituacaoFormal {

	EM_ANALISE,
	EM_ANALISE_OPERACIONAL,
	EM_TRATAMENTO,
	CONCLUIDA;
	
	public static SituacaoFormal getRandomSituacaoFormal() {
		return SituacaoFormal.values()[new Random().nextInt(SituacaoFormal.values().length)];
	}
	
}
