package com.faminto.votacaoalmocoapi.mock;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.faminto.votacaoalmocoapi.dto.VotacaoDTO;
import com.faminto.votacaoalmocoapi.model.Votacao;

public final class VotacaoMocker {

	public static final Long ID = 1L;
	public static final Long ID_DOIS = 2L;
	public static final LocalDateTime DATE = LocalDateTime.of(LocalDate.of(2020, Month.MARCH, 1), LocalTime.of(1, 0, 0));
	public static final LocalDateTime DATE_DOIS = LocalDateTime.of(LocalDate.of(2020, Month.MARCH, 2), LocalTime.of(2, 0, 0));
	
	private VotacaoMocker() {
		throw new UnsupportedOperationException();
	}
	
	public static final Votacao ENTIDADE = Votacao.builder()
			.id(ID)
			.inclusao(DATE)
			.restaurante(RestauranteMocker.ENTIDADE)
			.usuario(UsuarioMocker.ENTIDADE)
			.build();
	
	public static final VotacaoDTO DTO = VotacaoDTO.builder()
			.id(ID)
			.inclusao(DATE)
			.restaurante(RestauranteMocker.DTO)
			.usuario(UsuarioMocker.DTO)
			.build();
	
	public static final Votacao ENTIDADE_DOIS = Votacao.builder()
			.id(ID_DOIS)
			.inclusao(DATE_DOIS)
			.restaurante(RestauranteMocker.ENTIDADE_DOIS)
			.usuario(UsuarioMocker.ENTIDADE_DOIS)
			.build();
	
	public static final VotacaoDTO DTO_DOIS = VotacaoDTO.builder()
			.id(ID_DOIS)
			.inclusao(DATE_DOIS)
			.restaurante(RestauranteMocker.DTO_DOIS)
			.usuario(UsuarioMocker.DTO_DOIS)
			.build();
	
	public static final Votacao.VotacaoBuilder ENTIDADE_BUILDER = Votacao.builder()
			.id(ID)
			.restaurante(RestauranteMocker.ENTIDADE)
			.usuario(UsuarioMocker.ENTIDADE);
	
	public static List<Votacao> buildVotacaoList(Votacao... votacao) {
		List<Votacao> votacoes = new ArrayList<>();
		Arrays.stream(votacao).forEach(votacoes::add);
		
		return votacoes;
	}
	
	public static List<Votacao> buildVotacoesDoDia(LocalDate diaVotacao) {
		LocalDateTime dataHora = LocalDateTime.of(diaVotacao, LocalTime.of(9, 0));
		// Restaurante 1
		Votacao votacao = VotacaoMocker.ENTIDADE_BUILDER
				.restaurante(RestauranteMocker.ENTIDADE)
				.inclusao(dataHora.plusHours(1))
				.build();
		Votacao votacao2 = VotacaoMocker.ENTIDADE_BUILDER
				.restaurante(RestauranteMocker.ENTIDADE)
				.inclusao(dataHora.plusMinutes(15))
				.build();
		Votacao votacao3 = VotacaoMocker.ENTIDADE_BUILDER
				.restaurante(RestauranteMocker.ENTIDADE)
				.inclusao(dataHora.plusMinutes(25))
				.build();
		
		// Restaurante 2
		Votacao votacao4 = VotacaoMocker.ENTIDADE_BUILDER
				.restaurante(RestauranteMocker.ENTIDADE_DOIS)
				.inclusao(dataHora.plusHours(2))
				.build();
		Votacao votacao5 = VotacaoMocker.ENTIDADE_BUILDER
				.restaurante(RestauranteMocker.ENTIDADE_DOIS)
				.inclusao(dataHora.plusMinutes(45))
				.build();
		
		return VotacaoMocker.buildVotacaoList(votacao, votacao2, votacao3, votacao4, votacao5);
	}
	
	public static List<Votacao> buildVotacoesDoDiaComEmpate(LocalDate diaVotacao) {
		LocalDateTime dataHora = LocalDateTime.of(diaVotacao, LocalTime.of(9, 0));
		// Restaurante 1
		Votacao votacao = VotacaoMocker.ENTIDADE_BUILDER
				.restaurante(RestauranteMocker.ENTIDADE)
				.inclusao(dataHora.plusHours(1))
				.build();
				
		// Restaurante 2
		Votacao votacao2 = VotacaoMocker.ENTIDADE_BUILDER
				.restaurante(RestauranteMocker.ENTIDADE_DOIS)
				.inclusao(dataHora.plusHours(2))
				.build();
		
		return VotacaoMocker.buildVotacaoList(votacao, votacao2);
	}
	
	public static List<Votacao> buildVotacoesComDiasDiferentes(LocalDate diaVotacao) {
		LocalDateTime dataHora = LocalDateTime.of(diaVotacao, LocalTime.of(9, 0));
		// Restaurante 1
		Votacao votacao = VotacaoMocker.ENTIDADE_BUILDER
				.restaurante(RestauranteMocker.ENTIDADE)
				.inclusao(dataHora.plusHours(1))
				.build();
				
		Votacao votacao2 = VotacaoMocker.ENTIDADE_BUILDER
				.restaurante(RestauranteMocker.ENTIDADE)
				.inclusao(dataHora.minusDays(1))
				.build();
		
		return VotacaoMocker.buildVotacaoList(votacao, votacao2);
	}
	
}
