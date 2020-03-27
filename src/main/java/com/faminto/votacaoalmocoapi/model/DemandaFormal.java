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
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_demanda_formal")
public class DemandaFormal implements Serializable {

	private static final long serialVersionUID = 8170673092905090364L;
	
	@Id
    @Column(name = "id", insertable=false, updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "numero", nullable = false)
	private Long numero;
	
	@Column(name = "ano", nullable = false)
	private Integer ano;

}
