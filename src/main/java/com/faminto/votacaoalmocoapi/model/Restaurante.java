package com.faminto.votacaoalmocoapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Restaurante implements Serializable {

	private static final long serialVersionUID = -3811938747379523879L;

	@Id
    @Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@Column(name = "nome", nullable = false, length = 150)
	private String nome;
	
}