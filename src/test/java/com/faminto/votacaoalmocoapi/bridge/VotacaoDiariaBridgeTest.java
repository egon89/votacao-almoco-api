package com.faminto.votacaoalmocoapi.bridge;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest // com contexto
public class VotacaoDiariaBridgeTest {

	@InjectMocks
	private VotacaoDiariaBridge bridge;
	
	@Test
	public void givenUsuarioAndVotacoes_whenPodeVotar_thenReturnFalse() {
		assertEquals(bridge.podeVotar(null, null), Boolean.FALSE);
	}
	
}
