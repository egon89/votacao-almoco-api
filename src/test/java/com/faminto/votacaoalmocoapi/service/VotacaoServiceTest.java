package com.faminto.votacaoalmocoapi.service;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.mock.EleicaoMocker;
import com.faminto.votacaoalmocoapi.mock.VotacaoMocker;
import com.faminto.votacaoalmocoapi.repository.VotacaoRepository;
import com.faminto.votacaoalmocoapi.validator.VotacaoValidator;

@ExtendWith(MockitoExtension.class)
public class VotacaoServiceTest {
	
	@InjectMocks
	private VotacaoService service;
	
	@Mock
	private VotacaoRepository repository;
	
	@Mock
	private UsuarioService usuarioService;
	
	@Mock
	private RestauranteService restauranteService;
	
	@Mock
	private VotacaoValidator validator;
	
	@Test
	public void givenId_whenFindByUsuarioId_thenChamaRepository() {
		service.findByUsuarioId(1L);
		
		verify(repository, atLeastOnce()).findByUsuarioId(1L);
	}
	
	@Test
	public void whenFindAll_thenChamaRepository() {
		service.findAll();
		
		verify(repository, atLeastOnce()).findAll();
	}
	
	@Test
	public void givenVotacao_whenSalvar_thenChamaRepository() {
		service.salvar(VotacaoMocker.ENTIDADE);
		
		verify(repository, atLeastOnce()).save(VotacaoMocker.ENTIDADE);
	}
	
}
