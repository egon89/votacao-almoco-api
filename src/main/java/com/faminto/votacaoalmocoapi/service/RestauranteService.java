package com.faminto.votacaoalmocoapi.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;
import com.faminto.votacaoalmocoapi.model.Restaurante;
import com.faminto.votacaoalmocoapi.repository.RestauranteRepository;

@Service
public class RestauranteService {

	private RestauranteRepository repository;

	@Autowired
	public RestauranteService(RestauranteRepository repository) {
		this.repository = repository;
	}
	
	public Restaurante getRestauranteById(Long id) {
		return findById(id).orElseThrow(() -> new BusinessException(MessageKey.RESTAURANTE_NOT_FOUND, id));
	}
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		return repository.save(restaurante);
	}
	
	@Transactional(readOnly = true)
	public Optional<Restaurante> findById(Long id) {
		return repository.findById(id);
	}
	
	public void salvarNovoRestaurante() {
		salvar(Restaurante.builder().nome("Restaurante "+ new Random().nextInt(100)).build());
	}
	
}
