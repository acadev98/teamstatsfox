package com.acadev.teamstatsfox.database.entity;

import java.util.Date;

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
	private Date birthday;
	@Column(name = "PLAYING_SINCE")
	private Date playingSince;

}
