package com.faminto.votacaoalmocoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Restaurante;
import com.faminto.votacaoalmocoapi.repository.EleicaoRepository;

@Service
public class EleicaoService {

	private EleicaoRepository repository;
	private RestauranteService restauranteService;

	@Autowired
	public EleicaoService(EleicaoRepository repository, RestauranteService restauranteService) {
		this.repository = repository;
		this.restauranteService = restauranteService;
	}
	
	@Transactional
	public Eleicao salvar(Eleicao eleicao) {
		return repository.save(eleicao);
	}

	public void salvarNovaEleicao() {
		Long id = 2L;
		Restaurante restaurante = restauranteService.findById(id)
				.orElseThrow(() -> new BusinessException(MessageKey.RESTAURANTE_NOT_FOUND, id));
		Eleicao eleicao = Eleicao.builder()
			.restaurante(restaurante)
			.build();
		repository.save(eleicao);
	}
	
}