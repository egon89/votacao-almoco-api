package com.faminto.votacaoalmocoapi.validator;

import com.faminto.votacaoalmocoapi.dto.validation.ValidatorDTO;

public interface IValidator<T extends ValidatorDTO> {

	void validar(T validator);
	
}
