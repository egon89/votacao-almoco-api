package com.faminto.votacaoalmocoapi.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.dto.UsuarioDTO;
import com.faminto.votacaoalmocoapi.mock.UsuarioMocker;
import com.faminto.votacaoalmocoapi.model.Usuario;

@ExtendWith(MockitoExtension.class)
public class UsuarioAdapterTest {

	@InjectMocks
	private UsuarioAdapter adapter;
	
	@Test
	public void givenUsuario_whenValueOf_thenReturnUsuarioDTO() {
		Usuario entidade = UsuarioMocker.ENTIDADE;
		UsuarioDTO dto = adapter.valueOf(entidade);
		
		assertNotNull(dto);
		assertEquals(entidade.getId(), dto.getId());
		assertEquals(entidade.getLogin(), dto.getLogin());
	}
	
	@Test
	public void givenUsuarioDTO_whentoEntity_thenReturnUsuario() {
		UsuarioDTO dto = UsuarioMocker.DTO;
		Usuario entidade = adapter.toEntity(dto);
		
		assertNotNull(entidade);
		assertEquals(dto.getId(), entidade.getId());
		assertEquals(dto.getLogin(), entidade.getLogin());
	}
	
}
