package com.faminto.votacaoalmocoapi.configuration;

import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class VotacaoHoraLimite {

	@Value("${votacao.horaLimite}")
	private LocalTime horaLimite;
	
}
