package com.faminto.votacaoalmocoapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = -7576201862591669710L;

	@Id
    @Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@EqualsAndHashCode.Exclude
	@Column(name = "login", nullable = false, length = 150)
	private String login;
	
}
