package com.faminto.votacaoalmocoapi.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.dto.EleicaoDTO;
import com.faminto.votacaoalmocoapi.mock.EleicaoMocker;
import com.faminto.votacaoalmocoapi.mock.RestauranteMocker;
import com.faminto.votacaoalmocoapi.model.Eleicao;

@ExtendWith(MockitoExtension.class)
public class EleicaoAdapterTest {

	@InjectMocks
	private EleicaoAdapter adapter;
	
	@Mock
	private RestauranteAdapter restauranteAdapter;
	
	@Test
	public void givenEleicao_whenValueOf_thenReturnEleicaoDTO() {
		when(restauranteAdapter.valueOf(any())).thenReturn(RestauranteMocker.DTO);
		
		Eleicao entidade = EleicaoMocker.ENTIDADE;
		EleicaoDTO dto = adapter.valueOf(entidade);
		
		assertNotNull(dto);
		assertEquals(entidade.getId(), dto.getId());
		assertEquals(entidade.getInclusao(), dto.getInclusao());
		assertEquals(entidade.getRestaurante().getId(), dto.getRestaurante().getId());
		assertEquals(entidade.getVotos(), dto.getVotos());
	}
	
	@Test
	public void givenEleicaoDTO_whentoEntity_thenReturnEleicao() {
		when(restauranteAdapter.toEntity(any())).thenReturn(RestauranteMocker.ENTIDADE);
		
		EleicaoDTO dto = EleicaoMocker.DTO;
		Eleicao entidade = adapter.toEntity(dto);
		
		assertNotNull(entidade);
		assertEquals(dto.getId(), entidade.getId());
		assertEquals(dto.getInclusao(), entidade.getInclusao());
		assertEquals(dto.getRestaurante().getId(), entidade.getRestaurante().getId());
		assertEquals(dto.getVotos(), entidade.getVotos());
	}
	
}
