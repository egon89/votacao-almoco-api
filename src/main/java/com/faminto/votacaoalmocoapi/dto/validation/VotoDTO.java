package com.faminto.votacaoalmocoapi.dto.validation;

import java.time.LocalDate;
import java.util.List;

import com.faminto.votacaoalmocoapi.model.Restaurante;
import com.faminto.votacaoalmocoapi.model.Usuario;
import com.faminto.votacaoalmocoapi.model.Votacao;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(callSuper=false)
public class VotoDTO extends ValidatorDTO {

	private LocalDate dia; 
	private Usuario usuario;
	private Restaurante restaurante;
	private List<Votacao> votacoes;
	
}
