package com.faminto.votacaoalmocoapi.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsuarioDTO implements Serializable {

	private static final long serialVersionUID = 77149326665156766L;
	
	private Long id;
	private String login;

	//TODO: Validação e mensagens de validação
}
