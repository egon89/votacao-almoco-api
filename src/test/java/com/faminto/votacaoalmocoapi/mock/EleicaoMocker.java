package com.faminto.votacaoalmocoapi.mock;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.faminto.votacaoalmocoapi.dto.EleicaoDTO;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Restaurante;

public final class EleicaoMocker {

	public static final Long ID = 1L;
	public static LocalDate DATE = LocalDate.of(2020, Month.MARCH, 1);
	
	private EleicaoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Eleicao ENTIDADE = Eleicao.builder()
			.id(ID)
			.inclusao(DATE)
			.restaurante(RestauranteMocker.ENTIDADE)
			.build();
	
	public static final EleicaoDTO DTO = EleicaoDTO.builder()
			.id(ID)
			.inclusao(DATE)
			.restaurante(RestauranteMocker.DTO)
			.build();
	
	
	public static List<Eleicao> buildEleicaoList(Eleicao... eleicao) {
		List<Eleicao> eleicoes = new ArrayList<>();
		Arrays.stream(eleicao).forEach(eleicoes::add);
		
		return eleicoes;
	}
	
	public static List<Eleicao> buildDefaultEleicoes(LocalDate inicio, LocalDate fim) {
		if (inicio.isEqual(fim) || inicio.isAfter(fim)) {
			throw new IllegalArgumentException("Informe um período válido");
		}
		List<Eleicao> eleicoes = new ArrayList<>();
	    int diferenca = Period.between(inicio, fim).getDays();
	    for (int i = 1; i <= diferenca + 1; i++) {
	    	Eleicao eleicao = Eleicao.builder()
	    		.id(Long.valueOf(i))
	    		.restaurante(Restaurante.builder().id(Long.valueOf(i)).nome("Restaurante "+ i).build())
	    		.inclusao(inicio)
	    		.build();
	    	eleicoes.add(eleicao);
	    	inicio = inicio.plusDays(1);
		}
	    
		return eleicoes;
	}
	
}
