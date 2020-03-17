package com.faminto.votacaoalmocoapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faminto.votacaoalmocoapi.service.EleicaoService;

@RestController
@RequestMapping("/api/eleicoes")
public class EleicaoController {

	private EleicaoService service;

	@Autowired
	public EleicaoController(EleicaoService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<String> test() {
		service.salvarNovaEleicao();
		return ResponseEntity.ok("OK");
	}
	
}
