package com.faminto.votacaoalmocoapi.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.dto.VotacaoDTO;
import com.faminto.votacaoalmocoapi.mock.RestauranteMocker;
import com.faminto.votacaoalmocoapi.mock.UsuarioMocker;
import com.faminto.votacaoalmocoapi.mock.VotacaoMocker;
import com.faminto.votacaoalmocoapi.model.Votacao;

@ExtendWith(MockitoExtension.class)
public class VotacaoAdapterTest {

	@InjectMocks
	private VotacaoAdapter adapter;
	
	@Mock
	private RestauranteAdapter restauranteAdapter;
	
	@Mock
	private UsuarioAdapter usuarioAdapter;
	
	@Test
	public void givenVotacao_whenValueOf_thenReturnVotacaoDTO() {
		when(restauranteAdapter.valueOf(any())).thenReturn(RestauranteMocker.DTO);
		when(usuarioAdapter.valueOf(any())).thenReturn(UsuarioMocker.DTO);
		
		Votacao entidade = VotacaoMocker.ENTIDADE;
		VotacaoDTO dto = adapter.valueOf(entidade);
		
		assertNotNull(dto);
		assertEquals(entidade.getId(), dto.getId());
		assertEquals(entidade.getInclusao(), dto.getInclusao());
		assertEquals(entidade.getRestaurante().getId(), dto.getRestaurante().getId());
		assertEquals(entidade.getUsuario().getId(), dto.getUsuario().getId());
	}
	
	@Test
	public void givenVotacaoDTO_whentoEntity_thenReturnVotacao() {
		when(restauranteAdapter.toEntity(any())).thenReturn(RestauranteMocker.ENTIDADE);
		when(usuarioAdapter.toEntity(any())).thenReturn(UsuarioMocker.ENTIDADE);
		
		VotacaoDTO dto = VotacaoMocker.DTO;
		Votacao entidade = adapter.toEntity(dto);
		
		assertNotNull(entidade);
		assertEquals(dto.getId(), entidade.getId());
		assertEquals(dto.getInclusao(), entidade.getInclusao());
		assertEquals(dto.getRestaurante().getId(), entidade.getRestaurante().getId());
		assertEquals(dto.getUsuario().getId(), entidade.getUsuario().getId());
	}
	
}
