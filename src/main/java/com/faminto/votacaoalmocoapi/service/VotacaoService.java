package com.faminto.votacaoalmocoapi.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faminto.votacaoalmocoapi.dto.validation.VotoDTO;
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
	private VotacaoValidator validator;

	@Autowired
	public VotacaoService(VotacaoRepository repository, UsuarioService usuarioService,
			RestauranteService restauranteService, VotacaoValidator validator) {
		this.repository = repository;
		this.usuarioService = usuarioService;
		this.restauranteService = restauranteService;
		this.validator = validator;
	}

	public Votacao votar(Long idRestaurante) {
		Usuario usuario = usuarioService.getUsuarioAutenticado();
		List<Votacao> votacoesDoUsuario = findByUsuarioId(usuario.getId());
		Restaurante restaurante = restauranteService.getRestauranteById(idRestaurante);
		
		validator.validar(VotoDTO.builder().dia(LocalDate.now()).usuario(usuario).restaurante(restaurante)
				.votacoes(votacoesDoUsuario).build());
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
