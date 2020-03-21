package com.faminto.votacaoalmocoapi.mock;

import com.faminto.votacaoalmocoapi.dto.UsuarioDTO;
import com.faminto.votacaoalmocoapi.model.Usuario;

public final class UsuarioMocker {

	public static final Long ID = 1L;
	public static final Long ID_DOIS = 2L;
	public static final Long ID_NOT_FOUND = 0L;
	public static final String LOGIN = "usuario";
	
	private UsuarioMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Usuario ENTIDADE = Usuario.builder()
			.id(ID)
			.login(LOGIN)
			.build();
	
	public static final UsuarioDTO DTO = UsuarioDTO.builder()
			.id(ID)
			.login(LOGIN)
			.build();
	
	public static final Usuario ENTIDADE_DOIS = Usuario.builder()
			.id(ID_DOIS)
			.login(LOGIN)
			.build();
	
	public static final UsuarioDTO DTO_DOIS = UsuarioDTO.builder()
			.id(ID_DOIS)
			.login(LOGIN)
			.build();
	
}
