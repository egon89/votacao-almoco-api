package com.faminto.votacaoalmocoapi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faminto.votacaoalmocoapi.model.AlteracaoFormal;

@Repository
public interface AlteracaoFormalRepository extends JpaRepository<AlteracaoFormal, Long> {

	List<AlteracaoFormal> findByDemandaFormalId(Long id);
	
}
