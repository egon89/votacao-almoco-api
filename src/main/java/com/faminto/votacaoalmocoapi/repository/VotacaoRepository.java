package com.faminto.votacaoalmocoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faminto.votacaoalmocoapi.model.Votacao;

@Repository
public interface VotacaoRepository extends JpaRepository<Votacao, Long> {

}
