package com.faminto.votacaoalmocoapi.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.dto.EleicaoDTO;
import com.faminto.votacaoalmocoapi.model.Eleicao;

@Component
public class EleicaoAdapter implements InterfaceAdapter<Eleicao, EleicaoDTO> {

	private RestauranteAdapter restauranteAdapter;
	
	@Autowired
	public EleicaoAdapter(RestauranteAdapter restauranteAdapter) {
		this.restauranteAdapter = restauranteAdapter;
	}

	@Override
	public Eleicao toEntity(EleicaoDTO dto) {
		return Eleicao.builder()
				.id(dto.getId())
				.inclusao(dto.getInclusao())
				.restaurante(restauranteAdapter.toEntity(dto.getRestaurante()))
				.build();
	}

	@Override
	public EleicaoDTO valueOf(Eleicao entity) {
		return EleicaoDTO.builder()
				.id(entity.getId())
				.inclusao(entity.getInclusao())
				.restaurante(restauranteAdapter.valueOf(entity.getRestaurante()))
				.build();
	}

}
