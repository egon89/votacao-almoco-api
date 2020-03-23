package com.faminto.votacaoalmocoapi.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.dto.RestauranteDTO;
import com.faminto.votacaoalmocoapi.mock.RestauranteMocker;
import com.faminto.votacaoalmocoapi.model.Restaurante;

@ExtendWith(MockitoExtension.class)
public class RestauranteAdapterTest {

	@InjectMocks
	private RestauranteAdapter adapter;
	
	@Test
	public void givenRestaurante_whenValueOf_thenReturnRestauranteDTO() {
		Restaurante entidade = RestauranteMocker.ENTIDADE;
		RestauranteDTO dto = adapter.valueOf(entidade);
		
		assertNotNull(dto);
		assertEquals(entidade.getId(), dto.getId());
		assertEquals(entidade.getNome(), dto.getNome());
	}
	
	@Test
	public void givenUsuarioDTO_whentoEntity_thenReturnUsuario() {
		RestauranteDTO dto = RestauranteMocker.DTO;
		Restaurante entidade = adapter.toEntity(dto);
		
		assertNotNull(entidade);
		assertEquals(dto.getId(), entidade.getId());
		assertEquals(entidade.getNome(), dto.getNome());
	}
	
}
