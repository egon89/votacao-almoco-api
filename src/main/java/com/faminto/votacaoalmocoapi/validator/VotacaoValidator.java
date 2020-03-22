package com.faminto.votacaoalmocoapi.validator;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.bridge.PeriodoVotacaoBridge;
import com.faminto.votacaoalmocoapi.bridge.RestauranteEleitoSemanaBridge;
import com.faminto.votacaoalmocoapi.bridge.VotacaoDiariaBridge;
import com.faminto.votacaoalmocoapi.dto.validation.VotoDTO;

@Component
public class VotacaoValidator implements IValidator<VotoDTO> {

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

	@Override
	public void validar(VotoDTO validator) {
		votacaoDiariaBridge.podeVotar(validator.getDia(), validator.getUsuario(), validator.getVotacoes());
		periodoVotacaoBridge.validaPeriodoValido(validator.getDia(), LocalDateTime.now());
		restauranteEleitoSemanaBridge.podeEleger(validator.getRestaurante(), validator.getDia());
	}

	
}
