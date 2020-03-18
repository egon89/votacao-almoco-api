package com.faminto.votacaoalmocoapi.mock;

import com.faminto.votacaoalmocoapi.dto.UsuarioDTO;
import com.faminto.votacaoalmocoapi.model.Usuario;

public final class UsuarioMocker {

	public static final Long ID = 1L;
	public static final Long ID_NOT_FOUND = 0L;
	public static final String NOME = "Nome do Usuário";
	
	private UsuarioMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Usuario ENTIDADE = Usuario.builder()
			.id(ID)
			.nome(NOME)
			.build();
	
	public static final UsuarioDTO DTO = UsuarioDTO.builder()
			.id(ID)
			.nome(NOME)
			.build();
	
}
