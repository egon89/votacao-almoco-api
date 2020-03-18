package com.faminto.votacaoalmocoapi.bridge;

import java.util.List;

import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.model.Votacao;

@Component
public class VotacaoDiariaBridge {

	public Boolean podeVotar(Usuario usuario, List<Votacao> votacoes) {
		
		
		return Boolean.FALSE;
	}
	
}
