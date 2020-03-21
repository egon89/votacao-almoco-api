package com.faminto.votacaoalmocoapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_votacao")
public class Votacao implements Serializable {

	private static final long serialVersionUID = -1064339542671426805L;
	
	@Id
    @Column(name = "id", insertable=false, updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_restaurante", referencedColumnName = "id")
	private Restaurante restaurante;
	
	@ManyToOne
	@JoinColumn(name = "id_usuario", referencedColumnName = "id")
	private Usuario usuario;
	
	@Column(name = "inclusao", nullable = false)
	private LocalDateTime inclusao;
	
	@PrePersist
	private void prePersist() {
		this.inclusao = LocalDateTime.now();
	}

}
