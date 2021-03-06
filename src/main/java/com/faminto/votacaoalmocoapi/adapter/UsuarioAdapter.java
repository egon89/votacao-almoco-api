package com.faminto.votacaoalmocoapi.adapter;

import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.dto.UsuarioDTO;
import com.faminto.votacaoalmocoapi.model.Usuario;

@Component
public class UsuarioAdapter implements InterfaceAdapter<Usuario, UsuarioDTO> {

	@Override
	public Usuario toEntity(UsuarioDTO dto) {
		return Usuario.builder()
				.id(dto.getId())
				.login(dto.getLogin())
				.build();
	}

	@Override
	public UsuarioDTO valueOf(Usuario entity) {
		return UsuarioDTO.builder()
				.id(entity.getId())
				.login(entity.getLogin())
				.build();
	}

}
