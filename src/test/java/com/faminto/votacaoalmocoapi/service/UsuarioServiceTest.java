package com.faminto.votacaoalmocoapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.mock.UsuarioMocker;
import com.faminto.votacaoalmocoapi.repository.UsuarioRepository;
import com.faminto.votacaoalmocoapi.security.IAutenticacaoFacade;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {
	
	@InjectMocks
	private UsuarioService service;
	
	@Mock
	private UsuarioRepository repository;
	
	@Mock
	private IAutenticacaoFacade autenticacao;
	
	@Test
	public void givenLoginExistente_whenFindByLogin_thenReturnUsuario() {
		when(repository.findOne(any())).thenReturn(Optional.of(UsuarioMocker.ENTIDADE));
		
		assertNotNull(service.findByLogin("usuario"));
	}
	
	@Test
	public void givenLoginInexistente_whenFindByLogin_thenReturnOptionalEmpty() {
		when(repository.findOne(any())).thenReturn(Optional.empty());
		
		assertEquals(Optional.empty(), service.findByLogin("usuario_inexistente"));
	}
	
	@Test
	public void whenGetUsuarioAutenticado_thenReturnUsuario() {
		when(autenticacao.getLogin()).thenReturn("usuario");
		when(repository.findOne(any())).thenReturn(Optional.of(UsuarioMocker.ENTIDADE));
		
		assertEquals(UsuarioMocker.ENTIDADE, service.getUsuarioAutenticado());
	}
	
	@Test
	public void whenGetUsuarioAutenticadoInexistente_thenThrowBusinessException() {
		when(autenticacao.getLogin()).thenReturn("usuario_inexistente");
		when(repository.findOne(any())).thenReturn(Optional.empty());
		
		assertThrows(BusinessException.class, () -> service.getUsuarioAutenticado());
	}
	
	@Test
	public void whenFindAll_thenChamaRepository() {
		service.findAll();
		
		verify(repository, atLeastOnce()).findAll();
	}

}
