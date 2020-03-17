package com.faminto.votacaoalmocoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faminto.votacaoalmocoapi.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private UsuarioService service;

	@Autowired
	public UsuarioController(UsuarioService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<String> test() {
		service.salvarNovoUsuario();
		return ResponseEntity.ok("OK");
	}
	
}
