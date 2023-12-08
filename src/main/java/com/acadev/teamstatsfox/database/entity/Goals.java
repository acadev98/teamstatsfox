package com.acadev.teamstatsfox.database.entity;

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
@Table(name = "GOALS")
public class Goals {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "MATCH_ID")
	private Long matchId;
	
	@Column(name = "PLAYER_ID")
	private Long playerId;
	
	@Column(name = "ASSIST_PLAYER_ID")
	private Long assistPlayerId;
	
	@Column(name = "FREE_KICK")
	private Boolean freeKick;
	
	private Integer minute;
	private Boolean our;
	private Boolean penalty;

}
