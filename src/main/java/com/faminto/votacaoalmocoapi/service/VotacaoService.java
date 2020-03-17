package com.faminto.votacaoalmocoapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;
import com.faminto.votacaoalmocoapi.model.Restaurante;
import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.model.Votacao;
import com.faminto.votacaoalmocoapi.repository.VotacaoRepository;

@Service
public class VotacaoService {

	private VotacaoRepository repository;
	private UsuarioService usuarioService;
	private RestauranteService restauranteService;

	@Autowired
	public VotacaoService(VotacaoRepository repository, UsuarioService usuarioService,
			RestauranteService restauranteService) {
		this.repository = repository;
		this.usuarioService = usuarioService;
		this.restauranteService = restauranteService;
	}
	
	@Transactional
	public Votacao salvar(Votacao votacao) {
		return repository.save(votacao);
	}

	public void salvarNovoVoto() {
		Long usuarioId = 5L;
		Long restauranteId = 2L;
		Usuario usuario = usuarioService.findById(usuarioId)
				.orElseThrow(() -> new BusinessException(MessageKey.USUARIO_NOT_FOUND, usuarioId));
		Restaurante restaurante = restauranteService.findById(restauranteId)
				.orElseThrow(() -> new BusinessException(MessageKey.RESTAURANTE_NOT_FOUND, restauranteId));
		Votacao votacao = Votacao.builder()
			.usuario(usuario)
			.restaurante(restaurante)
			.build();
		repository.save(votacao);
	}
	
}
