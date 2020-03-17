package com.faminto.votacaoalmocoapi.adapter;

public interface InterfaceAdapter <E, D> {

	E toEntity (D dto);
	
	D valueOf (E entity);
}
