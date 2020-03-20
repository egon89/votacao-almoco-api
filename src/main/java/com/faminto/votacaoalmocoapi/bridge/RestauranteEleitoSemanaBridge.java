package com.faminto.votacaoalmocoapi.bridge;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.helper.DataHelper;
import com.faminto.votacaoalmocoapi.message.MessageKey;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Restaurante;

@Component
public class RestauranteEleitoSemanaBridge {

	public void podeEleger(Restaurante restaurante, LocalDate diaEleicao, List<Eleicao> eleicoes) {
		LocalDate primeiroDiaUtilDaSemana = DataHelper.getPrimeiroDiaUtilDaSemana(diaEleicao);
		boolean isEleitoNaSemana = eleicoes.stream()
			.filter(eleicao -> eleicao.getRestaurante().equals(restaurante)
					&& (DataHelper.isMaiorOuIgual(eleicao.getInclusao(), primeiroDiaUtilDaSemana) 
							&& DataHelper.isMenorOuIgual(eleicao.getInclusao(), diaEleicao)))
			.count() > 0;
		
		if (isEleitoNaSemana) {
			throw new BusinessException(MessageKey.RESTAURANTE_ELEITO_SEMANA, restaurante.getNome());
		}
	}
	
}
