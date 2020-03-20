package com.faminto.votacaoalmocoapi.bridge;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;
import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.model.Votacao;

@Component
public class VotacaoDiariaBridge {

	public void podeVotar(LocalDate dia, Usuario usuario, List<Votacao> votacoes) {
		if (validar(usuario, votacoes)) {
			throw new IllegalArgumentException();
		}
		
		boolean podeVotar = votacoes.stream()
			.filter(voto -> voto.getUsuario().equals(usuario) && voto.getInclusao().toLocalDate().isEqual(dia))
			.count() < 1;
		
		if (!podeVotar) {
			throw new BusinessException(MessageKey.USUARIO_VOTO_DIARIO, usuario.getNome());
		}
	}

	private boolean validar(Usuario usuario, List<Votacao> votacoes) {
		return isNull(usuario) || isNull(votacoes);
	}
	
}
