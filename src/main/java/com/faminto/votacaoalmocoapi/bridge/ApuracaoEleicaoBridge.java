package com.faminto.votacaoalmocoapi.bridge;

import static java.util.Objects.isNull;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Restaurante;
import com.faminto.votacaoalmocoapi.model.Votacao;
import com.faminto.votacaoalmocoapi.repository.VotacaoRepository;

@Component
public class ApuracaoEleicaoBridge {
	
	private VotacaoRepository votacaoRepository;
	
	@Autowired
	public ApuracaoEleicaoBridge(VotacaoRepository votacaoRepository) {
		super();
		this.votacaoRepository = votacaoRepository;
	}

	public Optional<Eleicao> apurar(LocalDate dia) {
		if (isNull(dia)) {
			throw new IllegalArgumentException();
		}
		List<Votacao> votacoes = votacaoRepository.findAll();
		Map<Restaurante, Long> apuracao = mapearVotacao(dia, votacoes);
		Optional<Long> maiorVotoOpt = apurarMaiorVoto(apuracao);
		if (maiorVotoOpt.isPresent()) {
			Long maiorVoto = maiorVotoOpt.get();
			validarEmpateVotacao(apuracao, maiorVoto);

			return getEleicaoVencedora(apuracao, maiorVoto);
		}

		return Optional.empty();
	}
	
	private Map<Restaurante, Long> mapearVotacao(LocalDate dia, List<Votacao> votacoes) {
		return votacoes.stream().filter(votacao -> votacao.getInclusao().toLocalDate().isEqual(dia))
				.collect(Collectors.groupingBy(Votacao::getRestaurante, Collectors.counting()));
	}
	
	private Optional<Long> apurarMaiorVoto(Map<Restaurante, Long> apuracao) {
		return apuracao.values().stream().max(Comparator.naturalOrder());
	}

	private void validarEmpateVotacao(Map<Restaurante, Long> apuracao, Long maiorVoto) {
		if (apuracao.values().stream().filter(voto -> voto.equals(maiorVoto)).count() > 1) {
			throw new BusinessException(MessageKey.ELEICAO_EMPATE);
		}
	}
	
	private Optional<Eleicao> getEleicaoVencedora(Map<Restaurante, Long> apuracao, Long maiorVoto) {
		return apuracao.entrySet().stream().filter(ap -> ap.getValue().equals(maiorVoto))
				.map(ap -> Eleicao.builder().restaurante(ap.getKey()).votos(maiorVoto).build()).findFirst();
	}

}
