package com.faminto.votacaoalmocoapi.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VotacaoDTO implements Serializable {
	
	private static final long serialVersionUID = 8110790644730964248L;
	
	private Long id;
	private RestauranteDTO restaurante;
	private UsuarioDTO usuario;
	private LocalDateTime inclusao;

}
