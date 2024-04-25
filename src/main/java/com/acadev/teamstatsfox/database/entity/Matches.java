package com.acadev.teamstatsfox.database.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "MATCHES")
public class Matches {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime datetime;

	private Long opponentId;

	private Long tournmentId;

	private String description;

	private Long captain;

	@Column(name = "OUR_GOALS")
	private Integer ourGoals;

	@Column(name = "RIVAL_GOALS")
	private Integer rivalGoals;

	@Column(name = "NEXT_MATCH")
	private Boolean nextMatch;

}
