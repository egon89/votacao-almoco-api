package com.faminto.votacaoalmocoapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faminto.votacaoalmocoapi.dto.validation.VotoDTO;
import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.model.Restaurante;
import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.model.Votacao;
import com.faminto.votacaoalmocoapi.repository.VotacaoRepository;
import com.faminto.votacaoalmocoapi.validator.VotacaoValidator;

@Service
public class VotacaoService {

	private VotacaoRepository repository;
	private UsuarioService usuarioService;
	private RestauranteService restauranteService;
	private EleicaoService eleicaoService;
	private VotacaoValidator validator;

	@Autowired
	public VotacaoService(VotacaoRepository repository, UsuarioService usuarioService,
			RestauranteService restauranteService, EleicaoService eleicaoService, VotacaoValidator validator) {
		this.repository = repository;
		this.usuarioService = usuarioService;
		this.restauranteService = restauranteService;
		this.eleicaoService = eleicaoService;
		this.validator = validator;
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


	public Votacao votar(Long idRestaurante) {
		Usuario usuario = usuarioService.getUsuarioAutenticado();
		List<Votacao> votacoesDoUsuario = findByUsuarioId(usuario.getId());
		Restaurante restaurante = restauranteService.getRestauranteById(idRestaurante);
		List<Eleicao> eleicoes = eleicaoService.findAll();

		System.out.println(eleicoes);
		
		validator.validar(VotoDTO.builder().dia(LocalDate.now()).usuario(usuario).restaurante(restaurante)
				.votacoes(votacoesDoUsuario).eleicoes(eleicoes).build());
		Votacao votacao = Votacao.builder().inclusao(LocalDateTime.now()).restaurante(restaurante).usuario(usuario)
				.build();
		
		return salvar(votacao);
	}

	@Transactional(readOnly = true)
	public List<Votacao> findByUsuarioId(Long id) {
		return repository.findByUsuarioId(id);
	}
	
	@Transactional(readOnly = true)
	public List<Votacao> findAll() {
		return repository.findAll();
	}
	
	@Transactional
	public Votacao salvar(Votacao votacao) {
		return repository.save(votacao);
	}
	
}
