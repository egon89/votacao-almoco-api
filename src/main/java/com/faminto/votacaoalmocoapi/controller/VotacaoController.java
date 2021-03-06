package com.faminto.votacaoalmocoapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faminto.votacaoalmocoapi.adapter.VotacaoAdapter;
import com.faminto.votacaoalmocoapi.dto.VotacaoDTO;
import com.faminto.votacaoalmocoapi.model.Votacao;
import com.faminto.votacaoalmocoapi.service.VotacaoService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/votacoes")
public class VotacaoController {

	private VotacaoService service;
	private VotacaoAdapter adapter;
	
	@Autowired
	public VotacaoController(VotacaoService service, VotacaoAdapter adapter) {
		this.service = service;
		this.adapter = adapter;
	}

	@GetMapping
	@ApiOperation(value = "Retorna todas as votações")
	public ResponseEntity<List<VotacaoDTO>> votacoes() {
		List<VotacaoDTO> votacoes = service.findAll().stream()
			.map(adapter::valueOf)
			.collect(Collectors.toList());
		
		return ResponseEntity.ok(votacoes);
	}
	
	@PostMapping("/restaurante/{id}")
	@ApiOperation(value = "Vota em um restaurante")
	public ResponseEntity<VotacaoDTO> votar(@PathVariable(name="id") Long idRestaurante) {
		Votacao votacao = service.votar(idRestaurante);

		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(adapter.valueOf(votacao));
	}
	
}
