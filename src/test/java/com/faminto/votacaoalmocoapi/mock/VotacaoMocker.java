package com.faminto.votacaoalmocoapi.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import com.faminto.votacaoalmocoapi.dto.VotacaoDTO;
import com.faminto.votacaoalmocoapi.model.Votacao;

public final class VotacaoMocker {

	public static final Long ID = 1L;
	public static LocalDateTime DATE = LocalDateTime.of(LocalDate.of(2020, Month.MARCH, 1), LocalTime.of(1, 0, 0));
	
	private VotacaoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Votacao ENTIDADE = Votacao.builder()
			.id(ID)
			.inclusao(DATE)
			.restaurante(RestauranteMocker.ENTIDADE)
			.usuario(UsuarioMocker.ENTIDADE)
			.build();
	
	public static final VotacaoDTO DTO = VotacaoDTO.builder()
			.id(ID)
			.inclusao(DATE)
			.restaurante(RestauranteMocker.DTO)
			.usuario(UsuarioMocker.DTO)
			.build();
	
	public static final Votacao.VotacaoBuilder ENTIDADE_BUILDER = Votacao.builder()
			.id(ID)
			.restaurante(RestauranteMocker.ENTIDADE)
			.usuario(UsuarioMocker.ENTIDADE);
	
}
