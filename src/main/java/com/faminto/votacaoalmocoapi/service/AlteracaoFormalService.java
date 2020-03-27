package com.faminto.votacaoalmocoapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faminto.votacaoalmocoapi.dto.ContadorDTO;
import com.faminto.votacaoalmocoapi.model.AlteracaoFormal;
import com.faminto.votacaoalmocoapi.model.enumerations.SituacaoFormal;
import com.faminto.votacaoalmocoapi.repository.AlteracaoFormalRepository;

@Service
public class AlteracaoFormalService {

	private AlteracaoFormalRepository repository;

	@Autowired
	public AlteracaoFormalService(AlteracaoFormalRepository repository) {
		this.repository = repository;
	}

	public List<AlteracaoFormal> findAll() {
		return repository.findAll();
	}
	
	public List<AlteracaoFormal> findByDemandaFormalId(Long id) {
		return repository.findByDemandaFormalId(id);
	}
	
	public void process(List<AlteracaoFormal> alteracoes) {
		Collections.sort(alteracoes, (x,y) -> x.getInclusao().compareTo(y.getInclusao()));
		
		List<ContadorDTO> contadores = new ArrayList<>();
		AlteracaoFormal alteracaoFormal = alteracoes.stream().findFirst().orElseThrow(() -> new RuntimeException());
		SituacaoFormal situacaoAtual = alteracaoFormal.getSituacao();
		ContadorDTO contador = ContadorDTO.builder().situacao(situacaoAtual).inicio(alteracaoFormal.getInclusao().toLocalDate()).build();
		
		for (AlteracaoFormal alteracao : alteracoes) {
			// se situação é a mesma
			// continua somando dia e vai para próximo registro
			if (contador.getSituacao().equals(alteracao.getSituacao()))
				continue;
			
			// se situação é diferente
			// contador é finalizado e adicionado na lista
			contador.setFim(alteracao.getInclusao().toLocalDate());
			long diferenca = contador.getInicio().until(contador.getFim(), ChronoUnit.DAYS);
			contador.setDias(diferenca);
			contadores.add(contador);
			// novo contador criado com as informações da alteração atual
			contador = ContadorDTO.builder().situacao(alteracao.getSituacao()).inicio(alteracao.getInclusao().toLocalDate()).build();
		}
		
//		alteracoes.forEach(System.out::println);
		System.out.println("#");
		contadores.forEach(System.out::println);
		System.out.println("##");
		System.out.println("##");
	}
	
	public void write(String file, String content) {
	    Path path = Paths.get("C:\\Users\\Everton\\Desktop\\"+ file);
	    byte[] strToBytes = content.getBytes();
	    
	    try {
			Files.write(path, strToBytes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	}
	
}
