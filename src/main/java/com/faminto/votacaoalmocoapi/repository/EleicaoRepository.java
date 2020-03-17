package com.faminto.votacaoalmocoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faminto.votacaoalmocoapi.model.Eleicao;

@Repository
public interface EleicaoRepository extends JpaRepository<Eleicao, Long> {

}
