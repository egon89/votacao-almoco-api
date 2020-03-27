package com.faminto.votacaoalmocoapi.dto;

import java.io.Serializable;
import java.time.LocalDate;

import com.faminto.votacaoalmocoapi.model.enumerations.SituacaoFormal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContadorDTO implements Serializable {

	private static final long serialVersionUID = -8129392072363313508L;
	
	private SituacaoFormal situacao;
	private LocalDate inicio;
	private LocalDate fim;
	private long dias;
	
	@Override
	public String toString() {
		return "ContadorDTO [inicio=" + inicio + ", fim=" + fim + ", dias=" + dias + ", situacao=" + situacao +"]";
	}
	
	
	
}
