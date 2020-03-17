package com.faminto.votacaoalmocoapi.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.repository.UsuarioRepository;

@Service
public class UsuarioService {

	private UsuarioRepository repository;
	
	@Autowired
	public UsuarioService(UsuarioRepository repository) {
		this.repository = repository;
	}
	
	public void salvarNovoUsuario() {
		salvar(Usuario.builder().nome("Usuário "+ new Random().nextInt(100)).build());
	}

	@Transactional
	public Usuario salvar(Usuario usuario) {
		return repository.save(usuario);
	}
	
	@Transactional(readOnly = true)
	public Optional<Usuario> findById(Long id) {
		return repository.findById(id);
	}
	
}
