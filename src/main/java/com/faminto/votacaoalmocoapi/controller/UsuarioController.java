package com.faminto.votacaoalmocoapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faminto.votacaoalmocoapi.adapter.UsuarioAdapter;
import com.faminto.votacaoalmocoapi.dto.UsuarioDTO;
import com.faminto.votacaoalmocoapi.service.UsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

	private UsuarioService service;
	private UsuarioAdapter adapter;

	@Autowired
	public UsuarioController(UsuarioService service, UsuarioAdapter adapter) {
		this.service = service;
		this.adapter = adapter;
	}
	
	@GetMapping
	public ResponseEntity<List<UsuarioDTO>> usuarios() {
		List<UsuarioDTO> restaurantes = service.findAll().stream().map(adapter::valueOf).collect(Collectors.toList());
		return ResponseEntity.ok(restaurantes);
	}
	
}
