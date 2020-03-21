package com.faminto.votacaoalmocoapi.validator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.bridge.PeriodoVotacaoBridge;
import com.faminto.votacaoalmocoapi.bridge.RestauranteEleitoSemanaBridge;
import com.faminto.votacaoalmocoapi.bridge.VotacaoDiariaBridge;
import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.model.Votacao;

@Component
public class VotacaoValidator {

	private VotacaoDiariaBridge votacaoDiariaBridge;
	private PeriodoVotacaoBridge periodoVotacaoBridge;
	private RestauranteEleitoSemanaBridge restauranteEleitoSemanaBridge;

	@Autowired
	public VotacaoValidator(VotacaoDiariaBridge votacaoDiariaBridge, PeriodoVotacaoBridge periodoVotacaoBridge,
			RestauranteEleitoSemanaBridge restauranteEleitoSemanaBridge) {
		this.votacaoDiariaBridge = votacaoDiariaBridge;
		this.periodoVotacaoBridge = periodoVotacaoBridge;
		this.restauranteEleitoSemanaBridge = restauranteEleitoSemanaBridge;
	}

	public void validate(LocalDate dia, Usuario usuario, List<Votacao> votacoes) {
		votacaoDiariaBridge.podeVotar(dia, usuario, votacoes);
		periodoVotacaoBridge.validaPeriodoValido(dia, LocalDateTime.now());
		// TODO restaurante semanal
	}
	
}
