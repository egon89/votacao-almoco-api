package com.faminto.votacaoalmocoapi.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.faminto.votacaoalmocoapi.model.DemandaFormal;
import com.faminto.votacaoalmocoapi.model.enumerations.SituacaoFormal;
import com.faminto.votacaoalmocoapi.repository.DemandaFormalRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DemandaFormalService {

	private DemandaFormalRepository repository;
	private AlteracaoFormalService alteracaoFormalService;

	@Autowired
	public DemandaFormalService(DemandaFormalRepository repository, AlteracaoFormalService alteracaoFormalService) {
		this.repository = repository;
		this.alteracaoFormalService = alteracaoFormalService;
	}

	public List<DemandaFormal> findAll() {
		return repository.findAll();
	}
	
	public void findAllPageable() {
		Pageable pageRequest = PageRequest.of(0, 2);
		Page<DemandaFormal> page = null;

		int totalDemandas = 40001;
		generateDemandas(totalDemandas);
//		generateAlteracoes(totalDemandas);
		
		/*
		do {
			page = repository.findAll(pageRequest);
			System.out.println(page.getPageable());
			log.info("Página: {}", page.getPageable().getPageNumber());
			page.getContent().forEach(demanda ->  alteracaoFormalService.process(alteracaoFormalService.findByDemandaFormalId(demanda.getId())));
			log.info("###");
			pageRequest = page.nextPageable();
		} while (page.hasNext());
		*/
		
		log.info("Fim");
	}
	
	private void generateAlteracoes(int total) {
		LocalDate data = LocalDate.of(2012, Month.DECEMBER, 1);
		StringBuilder content = new StringBuilder();
		int alteracaoFormalId = 1281289;
		int demandaId = 20001;
		for (int d = 1; d <= total; d++) {
			for (int i = 1; i <= getRandomNumberInRange(50, 200); i++) {
				String dia = data.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
				String situacao = SituacaoFormal.getRandomSituacaoFormal().name();
				
				content.append(
						String.format("insert into tb_alteracao_formal (id, inclusao, situacao, id_demanda_formal) values (%d, '%s', '%s', %d);",
								alteracaoFormalId, dia, situacao, demandaId)
						);
				content.append(System.lineSeparator());
				
				alteracaoFormalId++;
				data = data.plusDays(new Random().nextInt(60));
			}
			demandaId++;
			content.append("--"+ System.lineSeparator());
			data = LocalDate.of(2012, Month.DECEMBER, 1);
			write("alteracoes_2.txt", content.toString());
			content = new StringBuilder();
		}
		
	}
	
	private void generateDemandas(int total) {
		StringBuilder content = new StringBuilder();
		for (int i = 1; i < total; i++) {
			content.append(
					String.format("insert into tb_demanda_formal (id, numero, ano) values (%d, %d, %d);", i, i, getRandomNumberInRange(2019, 2020))
			);
			content.append(System.lineSeparator());
		}
		write("demandas.txt", content.toString());
	}
	
	private void write(String file, String content) {
	    Path path = Paths.get("C:\\Users\\Everton\\Desktop\\"+ file);
	    byte[] strToBytes = content.getBytes();
	    
	    try {
			Files.write(path, strToBytes, StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	 
	}
	
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
	
}
