package com.faminto.votacaoalmocoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faminto.votacaoalmocoapi.service.VotacaoService;

@RestController
@RequestMapping("/api/votacoes")
public class VotacaoController {

	private VotacaoService service;
	
	@Autowired
	public VotacaoController(VotacaoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<String> test() {
		service.salvarNovoVoto();
		return ResponseEntity.ok("OK");
	}
	
}
