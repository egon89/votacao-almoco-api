package com.faminto.votacaoalmocoapi.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorDetails {

	private final LocalDateTime data;
	private final String mensagem;

	public ErrorDetails(String message) {
		this.data = LocalDateTime.now();
		this.mensagem = message;
	}
	
}
