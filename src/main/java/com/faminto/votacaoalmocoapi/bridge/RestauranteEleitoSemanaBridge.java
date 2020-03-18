package com.faminto.votacaoalmocoapi.bridge;

import java.util.List;

import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Restaurante;

@Component
public class RestauranteEleitoSemanaBridge {

	public Boolean podeEleger(Restaurante restaurante, List<Eleicao> eleicoes) {
		
		return Boolean.TRUE;
	}
	
}
