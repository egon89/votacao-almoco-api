package com.faminto.votacaoalmocoapi.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RestauranteDTO implements Serializable {

	private static final long serialVersionUID = 5498441699041554363L;
	
	private Long id;
	private String nome;
	
}
