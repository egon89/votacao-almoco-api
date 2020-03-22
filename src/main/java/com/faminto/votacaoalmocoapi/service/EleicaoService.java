package com.faminto.votacaoalmocoapi.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.faminto.votacaoalmocoapi.bridge.ApuracaoEleicaoBridge;
import com.faminto.votacaoalmocoapi.exception.BusinessException;
import com.faminto.votacaoalmocoapi.message.MessageKey;
import com.faminto.votacaoalmocoapi.model.Eleicao;
import com.faminto.votacaoalmocoapi.repository.EleicaoRepository;

@Service
public class EleicaoService {

	private EleicaoRepository repository;
	private ApuracaoEleicaoBridge apuracaoEleicaoBridge;
	
	@Autowired
	public EleicaoService(EleicaoRepository repository, ApuracaoEleicaoBridge apuracaoEleicaoBridge) {
		this.repository = repository;
		this.apuracaoEleicaoBridge = apuracaoEleicaoBridge;
	}

	public Eleicao apurar() {
		if (findEleicaoDoDia().isPresent()) {
			throw new BusinessException(MessageKey.ELEICAO_REALIZADA);
		}
		Eleicao eleicao = apuracaoEleicaoBridge.apurar(LocalDate.now())
				.orElseThrow( () -> new BusinessException(MessageKey.ELEICAO_NAO_PROCESSADA));
		
		return salvar(eleicao);
	}
	
	@Transactional(readOnly = true)
	public List<Eleicao> findAll() {
		return repository.findAll(Sort.by(Sort.Direction.DESC, "inclusao"));
	}
	
	@Transactional(readOnly = true)
	public Optional<Eleicao> findEleicaoDoDia() {
		return repository.findOne(Example.of(Eleicao.builder().inclusao(LocalDate.now()).build()));
	}
	
	@Transactional
	public Eleicao salvar(Eleicao eleicao) {
		return repository.save(eleicao);
	}
	
}
