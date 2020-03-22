package com.faminto.votacaoalmocoapi.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faminto.votacaoalmocoapi.adapter.RestauranteAdapter;
import com.faminto.votacaoalmocoapi.dto.RestauranteDTO;
import com.faminto.votacaoalmocoapi.service.RestauranteService;

@RestController
@RequestMapping("/api/restaurantes")
public class RestauranteController {

	private RestauranteService service;
	private RestauranteAdapter adapter;

	@Autowired
	public RestauranteController(RestauranteService service, RestauranteAdapter adapter) {
		this.service = service;
		this.adapter = adapter;
	}

	@GetMapping
	public ResponseEntity<List<RestauranteDTO>> restaurantes() {
		List<RestauranteDTO> restaurantes = service.findAll().stream().map(adapter::valueOf)
				.collect(Collectors.toList());
		return ResponseEntity.ok(restaurantes);
	}
	
}
