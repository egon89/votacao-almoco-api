package com.faminto.votacaoalmocoapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;
import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.repository.UsuarioRepository;
import com.faminto.votacaoalmocoapi.security.IAutenticacaoFacade;

@Service
public class UsuarioService {

	private UsuarioRepository repository;
	private IAutenticacaoFacade autenticacao;
	
	@Autowired
	public UsuarioService(UsuarioRepository repository, IAutenticacaoFacade autenticacao) {
		this.repository = repository;
		this.autenticacao = autenticacao;
	}

	public Usuario getUsuarioAutenticado() {
		String login = autenticacao.getLogin();
		return findByLogin(login).orElseThrow(() -> new BusinessException(MessageKey.USUARIO_LOGIN_NOT_FOUND, login));
	}
	
	@Transactional(readOnly = true)
	public Optional<Usuario> findByLogin(String login) {
		return repository.findOne(Example.of(Usuario.builder().login(login).build()));
	}
	
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
}
