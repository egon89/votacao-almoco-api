package com.faminto.votacaoalmocoapi.bridge;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.helper.DataHelper;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Restaurante;

@Component
public class RestauranteEleitoSemanaBridge {

	public Boolean podeEleger(Restaurante restaurante, LocalDate diaEleicao, List<Eleicao> eleicoes) {
		LocalDate primeiroDiaUtilDaSemana = DataHelper.getPrimeiroDiaUtilDaSemana(diaEleicao);
		long contador = eleicoes.stream()
			.filter(eleicao -> eleicao.getRestaurante().equals(restaurante)
					&& (DataHelper.isMaiorOuIgual(eleicao.getInclusao(), primeiroDiaUtilDaSemana) 
							&& DataHelper.isMenorOuIgual(eleicao.getInclusao(), diaEleicao)))
			.count();
		
		return !(contador > 0);
	}
	
}
