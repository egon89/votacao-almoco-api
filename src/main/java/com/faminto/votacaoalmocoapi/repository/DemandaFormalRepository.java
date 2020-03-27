package com.faminto.votacaoalmocoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.faminto.votacaoalmocoapi.model.DemandaFormal;

@Repository
public interface DemandaFormalRepository extends JpaRepository<DemandaFormal, Long> {

}
