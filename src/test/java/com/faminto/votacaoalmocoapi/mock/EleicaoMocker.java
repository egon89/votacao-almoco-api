package com.faminto.votacaoalmocoapi.mock;

import java.time.LocalDate;
import java.time.Month;

import com.faminto.votacaoalmocoapi.dto.EleicaoDTO;
import com.faminto.votacaoalmocoapi.model.Eleicao;

public final class EleicaoMocker {

	public static final Long ID = 1L;
	public static LocalDate DATE = LocalDate.of(2020, Month.MARCH, 1);
	
	private EleicaoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Eleicao ENTIDADE = Eleicao.builder()
			.id(ID)
			.inclusao(DATE)
			.restaurante(RestauranteMocker.ENTIDADE)
			.build();
	
	public static final EleicaoDTO DTO = EleicaoDTO.builder()
			.id(ID)
			.inclusao(DATE)
			.restaurante(RestauranteMocker.DTO)
			.build();
	
}
