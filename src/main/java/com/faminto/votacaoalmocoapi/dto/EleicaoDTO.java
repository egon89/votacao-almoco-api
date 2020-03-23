package com.faminto.votacaoalmocoapi.dto;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EleicaoDTO implements Serializable {

	private static final long serialVersionUID = -5868643589264768242L;
	
	private Long id;
	private RestauranteDTO restaurante;
	private LocalDate inclusao;
	private Long votos;
	
}
