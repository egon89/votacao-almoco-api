package com.faminto.votacaoalmocoapi.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Eleicao implements Serializable {

	private static final long serialVersionUID = -8624134794458206788L;
	
	@Id
    @Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "inclusao", nullable = false)
	private LocalDate inclusao;
	
	@Column(name = "votos", nullable = false)
	private Long votos;
	
	@ManyToOne
	@JoinColumn(name = "id_restaurante", referencedColumnName = "id")
	private Restaurante restaurante;
	
	@PrePersist
	private void prePersist() {
		this.inclusao = LocalDate.now();
	}

}
