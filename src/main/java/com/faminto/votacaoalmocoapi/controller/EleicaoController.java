package com.faminto.votacaoalmocoapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faminto.votacaoalmocoapi.adapter.EleicaoAdapter;
import com.faminto.votacaoalmocoapi.dto.EleicaoDTO;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.service.EleicaoService;

@RestController
@RequestMapping("/api/eleicoes")
public class EleicaoController {

	private EleicaoService service;
	private EleicaoAdapter adapter;

	@Autowired
	public EleicaoController(EleicaoService service, EleicaoAdapter adapter) {
		this.service = service;
		this.adapter = adapter;
	}
	
	@GetMapping
	public ResponseEntity<List<EleicaoDTO>> eleicoes() {
		List<EleicaoDTO> eleicoes = service.findAll().stream()
				.map(adapter::valueOf).collect(Collectors.toList());
		
		return ResponseEntity.ok(eleicoes);
	}

	@PostMapping("/apurar")
	public ResponseEntity<EleicaoDTO> apurar() {
		Eleicao eleicao = service.apurar();
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(adapter.valueOf(eleicao));
	}
	
	
}
