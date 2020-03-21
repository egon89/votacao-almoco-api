package com.faminto.votacaoalmocoapi.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AutenticacaoFacade implements IAutenticacaoFacade {

	@Override
	public Authentication getAutenticacao() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	@Override
	public String getLogin() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
	
	
}
