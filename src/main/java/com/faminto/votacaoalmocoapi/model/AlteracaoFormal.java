package com.faminto.votacaoalmocoapi.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.faminto.votacaoalmocoapi.model.enumerations.SituacaoFormal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_alteracao_formal")
public class AlteracaoFormal implements Comparable<AlteracaoFormal>, Serializable {

	private static final long serialVersionUID = -3188521509255152668L;
	
	@Id
    @Column(name = "id", insertable=false, updatable=false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "inclusao", nullable = false)
	private LocalDateTime inclusao;
	
	@Enumerated(EnumType.STRING)
	private SituacaoFormal situacao;
	
	@ManyToOne
	@JoinColumn(name = "id_demanda_formal", referencedColumnName = "id")
	private DemandaFormal demandaFormal;

	@Override
	public int compareTo(AlteracaoFormal o) {
		return o.getInclusao().compareTo(this.getInclusao());
	}

}
