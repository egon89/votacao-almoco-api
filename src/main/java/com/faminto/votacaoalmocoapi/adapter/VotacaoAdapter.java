package com.faminto.votacaoalmocoapi.adapter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.dto.VotacaoDTO;
import com.faminto.votacaoalmocoapi.model.Votacao;

@Component
public class VotacaoAdapter implements InterfaceAdapter<Votacao, VotacaoDTO> {

	private RestauranteAdapter restauranteAdapter;
	private UsuarioAdapter usuarioAdapter;
	
	@Autowired
	public VotacaoAdapter(RestauranteAdapter restauranteAdapter, UsuarioAdapter usuarioAdapter) {
		this.restauranteAdapter = restauranteAdapter;
		this.usuarioAdapter = usuarioAdapter;
	}

	@Override
	public Votacao toEntity(VotacaoDTO dto) {
		return Votacao.builder()
				.id(dto.getId())
				.inclusao(dto.getInclusao())
				.restaurante(restauranteAdapter.toEntity(dto.getRestaurante()))
				.usuario(usuarioAdapter.toEntity(dto.getUsuario()))
				.build();
	}

	@Override
	public VotacaoDTO valueOf(Votacao entity) {
		return VotacaoDTO.builder()
				.id(entity.getId())
				.inclusao(entity.getInclusao())
				.restaurante(restauranteAdapter.valueOf(entity.getRestaurante()))
				.usuario(usuarioAdapter.valueOf(entity.getUsuario()))
				.build();
	}

}
