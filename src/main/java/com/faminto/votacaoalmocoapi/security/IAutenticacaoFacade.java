package com.faminto.votacaoalmocoapi.security;

import org.springframework.security.core.Authentication;

public interface IAutenticacaoFacade {

	Authentication getAutenticacao();
	String getLogin();
	
}
