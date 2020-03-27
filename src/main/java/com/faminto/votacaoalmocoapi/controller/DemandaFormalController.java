package com.faminto.votacaoalmocoapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.faminto.votacaoalmocoapi.model.DemandaFormal;
import com.faminto.votacaoalmocoapi.service.DemandaFormalService;

@RestController
@RequestMapping("/api/demanda-formais")
public class DemandaFormalController {

	private DemandaFormalService service;

	@Autowired
	public DemandaFormalController(DemandaFormalService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<List<DemandaFormal>> findAll() {
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping("/pageable")
	public ResponseEntity<String> findAllPageable() {
		service.findAllPageable();
		
		return ResponseEntity.ok("OK");
	}
	
}
