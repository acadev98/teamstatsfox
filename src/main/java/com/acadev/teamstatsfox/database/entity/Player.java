package com.acadev.teamstatsfox.database.entity;

import java.time.LocalDate;

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
@Table(name = "PLAYERS")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String dni;

	private String lastname;

	private String name;

	private String position;

	@Column(name = "SECOND_POSITION")
	private String secondPosition;

	private Boolean active;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate birthday;

	@Column(name = "PLAYING_SINCE")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate playingSince;

}
