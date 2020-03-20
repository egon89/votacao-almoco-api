package com.faminto.votacaoalmocoapi.message;

import lombok.Getter;

@Getter
public enum MessageKey {

	USUARIO_NOT_FOUND("error.usuarioNotfound"),
	USUARIO_VOTO_DIARIO("error.usuarioVotoDiario"),
	RESTAURANTE_NOT_FOUND("error.restauranteNotfound"),
	RESTAURANTE_ELEITO_SEMANA("error.restauranteEleitoSemana"), 
	ELEICAO_EMPATE("error.eleicaoEmpate"), 
	VOTO_PERIODO_INVALIDO("error.periodoInvalido"); 
	
	
	private final String key;
	
	private MessageKey(String key) {
		this.key = key;
	}
	
}
