package com.faminto.votacaoalmocoapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.dto.validation.VotoDTO;
import com.faminto.votacaoalmocoapi.mock.RestauranteMocker;
import com.faminto.votacaoalmocoapi.mock.UsuarioMocker;
import com.faminto.votacaoalmocoapi.mock.VotacaoMocker;
import com.faminto.votacaoalmocoapi.model.Votacao;
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
	public void givenId_whenVotar_thenChamaRepository() {
		List<Votacao> votacaoesDoUsuario = new ArrayList<>();
		when(usuarioService.getUsuarioAutenticado()).thenReturn(UsuarioMocker.ENTIDADE);
		when(repository.findByUsuarioId(anyLong())).thenReturn(votacaoesDoUsuario);
		when(restauranteService.getRestauranteById(anyLong())).thenReturn(RestauranteMocker.ENTIDADE);
		doNothing().when(validator).validar(any(VotoDTO.class));
		when(repository.save(any(Votacao.class))).thenReturn(VotacaoMocker.ENTIDADE);
		Votacao votacao = service.votar(1L);
		
		assertEquals(1L, votacao.getRestaurante().getId());
	}
	
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
