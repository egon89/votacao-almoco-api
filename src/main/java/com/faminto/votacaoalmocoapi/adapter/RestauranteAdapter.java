package com.faminto.votacaoalmocoapi.adapter;

import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.dto.RestauranteDTO;
import com.faminto.votacaoalmocoapi.model.Restaurante;

@Component
public class RestauranteAdapter implements InterfaceAdapter<Restaurante, RestauranteDTO> {

	@Override
	public Restaurante toEntity(RestauranteDTO dto) {
		return Restaurante.builder()
				.id(dto.getId())
				.nome(dto.getNome())
				.build();
	}

	@Override
	public RestauranteDTO valueOf(Restaurante entity) {
		return RestauranteDTO.builder()
				.id(entity.getId())
				.nome(entity.getNome())
				.build();
	}

}
