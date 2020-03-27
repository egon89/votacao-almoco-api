package com.faminto.votacaoalmocoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faminto.votacaoalmocoapi.model.AlteracaoFormal;
import com.faminto.votacaoalmocoapi.service.AlteracaoFormalService;

@RestController
@RequestMapping("/api/alteracoes-formais")
public class AlteracaoFormalController {

	private AlteracaoFormalService service;

	@Autowired
	public AlteracaoFormalController(AlteracaoFormalService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<AlteracaoFormal>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	
}
