package com.faminto.votacaoalmocoapi.mock;

import com.faminto.votacaoalmocoapi.dto.RestauranteDTO;
import com.faminto.votacaoalmocoapi.model.Restaurante;

public final class RestauranteMocker {

	public static final Long ID = 1L;
	public static final Long ID_DOIS = 2L;
	public static final Long ID_NOT_FOUND = 0L;
	public static final String NOME = "Nome do Restaurante";
	
	private RestauranteMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Restaurante ENTIDADE = Restaurante.builder()
			.id(ID)
			.nome(NOME)
			.build();
	
	public static final RestauranteDTO DTO = RestauranteDTO.builder()
			.id(ID)
			.nome(NOME)
			.build();
	
	public static final Restaurante ENTIDADE_DOIS = Restaurante.builder()
			.id(ID_DOIS)
			.nome(NOME)
			.build();
	
	public static final RestauranteDTO DTO_DOIS = RestauranteDTO.builder()
			.id(ID_DOIS)
			.nome(NOME)
			.build();
	
	public static final Restaurante.RestauranteBuilder ENTIDADE_BUILDER = Restaurante.builder()
			.id(ID)
			.nome(NOME);
	
}
