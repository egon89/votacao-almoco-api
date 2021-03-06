package com.faminto.votacaoalmocoapi.message;

import lombok.Getter;

@Getter
public enum MessageKey {

	USUARIO_NOT_FOUND("error.usuarioNotfound"),
	USUARIO_LOGIN_NOT_FOUND("error.usuarioLoginNotfound"), 
	USUARIO_VOTO_DIARIO("error.usuarioVotoDiario"),
	RESTAURANTE_NOT_FOUND("error.restauranteNotfound"),
	RESTAURANTE_ELEITO_SEMANA("error.restauranteEleitoSemana"), 
	ELEICAO_EMPATE("error.eleicaoEmpate"), 
	ELEICAO_NAO_PROCESSADA("error.eleicaoNaoProcessada"),
	ELEICAO_REALIZADA("error.eleicaoRealizada"),
	VOTO_PERIODO_INVALIDO("error.periodoInvalido"); 
	
	private final String key;
	
	private MessageKey(String key) {
		this.key = key;
	}
	
}
