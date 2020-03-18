package com.faminto.votacaoalmocoapi.bridge;

import static java.util.Objects.*;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.model.Votacao;

@Component
public class VotacaoDiariaBridge {

	public Boolean podeVotar(LocalDate dia, Usuario usuario, List<Votacao> votacoes) {
		if (validar(usuario, votacoes)) {
			throw new IllegalArgumentException();
		}
		
		return votacoes.stream()
			.filter(voto -> voto.getUsuario().equals(usuario) && voto.getInclusao().toLocalDate().isEqual(dia))
			.count() < 1;
	}

	private boolean validar(Usuario usuario, List<Votacao> votacoes) {
		return isNull(usuario) || isNull(votacoes);
	}
	
}
