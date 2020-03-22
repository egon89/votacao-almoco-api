package com.faminto.votacaoalmocoapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Sort;

import com.faminto.votacaoalmocoapi.bridge.ApuracaoEleicaoBridge;
import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.mock.EleicaoMocker;
import com.faminto.votacaoalmocoapi.repository.EleicaoRepository;

@ExtendWith(MockitoExtension.class)
public class EleicaoServiceTest {
	
	@InjectMocks
	private EleicaoService service;
	
	@Mock
	private EleicaoRepository repository;
	
	@Mock
	private ApuracaoEleicaoBridge apuracaoEleicaoBridge;
	
	@Test
	public void whenApurar_thenReturnEleicao() {
		when(service.findEleicaoDoDia()).thenReturn(Optional.empty());
		when(apuracaoEleicaoBridge.apurar(Mockito.any(LocalDate.class))).thenReturn(Optional.of(EleicaoMocker.ENTIDADE));
		
		service.apurar();
		verify(repository, atLeastOnce()).save(EleicaoMocker.ENTIDADE);
	}
	
	@Test
	public void whenApurarEleicaoJaRealizadaNoDia_thenThrowBusinessException() {
		when(service.findEleicaoDoDia()).thenReturn(Optional.of(EleicaoMocker.ENTIDADE));
		
		assertThrows(BusinessException.class, () -> service.apurar());
	}
	
	@Test
	public void whenApurarEleicaoInvalida_thenThrowBusinessException() {
		when(service.findEleicaoDoDia()).thenReturn(Optional.empty());
		when(apuracaoEleicaoBridge.apurar(Mockito.any(LocalDate.class))).thenReturn(Optional.empty());
		
		assertThrows(BusinessException.class, () -> service.apurar());
	}
	
	@Test
	public void whenFindAll_thenChamaRepository() {
		service.findAll();
		
		verify(repository, atLeastOnce()).findAll(any(Sort.class));
	}
	
	@Test
	public void whenFindEleicaoDoDia_thenReturnEleicao() {
		when(repository.findOne(any())).thenReturn(Optional.of(EleicaoMocker.ENTIDADE));
		
		assertNotNull(service.findEleicaoDoDia());
	}
	
	@Test
	public void whenFindEleicaoDoDiaInexistente_thenReturnOptionalEmpty() {
		when(repository.findOne(any())).thenReturn(Optional.empty());
		
		assertEquals(Optional.empty(), service.findEleicaoDoDia());
	}
	
	@Test
	public void givenEleicao_whenSalvar_thenChamaRepository() {
		service.salvar(EleicaoMocker.ENTIDADE);
		
		verify(repository, atLeastOnce()).save(EleicaoMocker.ENTIDADE);
	}

}
