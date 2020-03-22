package com.faminto.votacaoalmocoapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.faminto.votacaoalmocoapi.mock.RestauranteMocker;
import com.faminto.votacaoalmocoapi.model.Restaurante;
import com.faminto.votacaoalmocoapi.repository.RestauranteRepository;

@ExtendWith(MockitoExtension.class)
public class RestauranteServiceTest {

	@InjectMocks
	private RestauranteService service;
	
	@Mock
	private RestauranteRepository repository;
	
	@Test
	public void givenId_whenFindById_thenReturnRestauranteExistente() {
		Optional<Restaurante> restauranteOpt = Optional.of(RestauranteMocker.ENTIDADE);
		when(repository.findById(anyLong())).thenReturn(restauranteOpt);
		
		assertNotNull(service.getRestauranteById(1L));
	}
	
	@Test
	public void givenIdInexistente_whenFindById_thenReturnOptionalEmpty() {
		Optional<Restaurante> restauranteOpt = Optional.empty();
		when(repository.findById(anyLong())).thenReturn(restauranteOpt);
		
		assertEquals(Optional.empty(), service.findById(100L));
	}
	
	@Test
	public void givenIdNulo_whenFindById_thenThrowIllegalArgumentException() {
		when(repository.findById(null)).thenThrow(IllegalArgumentException.class);
		
		assertThrows(IllegalArgumentException.class, () -> service.findById(null));
	}
	
	@Test
	public void givenId_whenGetRestauranteById_thenReturnRestauranteExistente() {
		Optional<Restaurante> restauranteOpt = Optional.of(RestauranteMocker.ENTIDADE);
		when(service.findById(anyLong())).thenReturn(restauranteOpt);
		
		assertNotNull(service.getRestauranteById(1L));
	}
	
	@Test
	public void givenIdInexistente_whenGetRestauranteById_thenReturnOptionalEmpty() {
		Optional<Restaurante> restauranteOpt = Optional.empty();
		when(service.findById(anyLong())).thenReturn(restauranteOpt);
		
		assertEquals(Optional.empty(), service.findById(100L));
	}
	
	@Test
	public void givenRestaurante_whenSalvar_thenChamaRepository() {
		service.salvar(RestauranteMocker.ENTIDADE);
		
		verify(repository, atLeastOnce()).save(RestauranteMocker.ENTIDADE);
	}
	
	@Test
	public void givenRestauranteNulo_whenSalvar_thenThrowIllegalArgumentException() {
		when(repository.save(null)).thenThrow(IllegalArgumentException.class);
		
		assertThrows(IllegalArgumentException.class, () -> repository.save(null));
	}
	
	@Test
	public void whenFindAll_thenChamaRepository() {
		service.findAll();
		
		verify(repository, atLeastOnce()).findAll();
	}
	
}
