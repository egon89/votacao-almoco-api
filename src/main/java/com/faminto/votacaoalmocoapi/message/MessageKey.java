package com.faminto.votacaoalmocoapi.message;

import lombok.Getter;

@Getter
public enum MessageKey {

	RESTAURANTE_NOT_FOUND("error.restauranteNotfound"),
	USUARIO_NOT_FOUND("error.usuarioNotfound"),
	INVALID_PARAMETERS("error.invalidParameters");
	
	private final String key;
	
	private MessageKey(String key) {
		this.key = key;
	}
	
}
