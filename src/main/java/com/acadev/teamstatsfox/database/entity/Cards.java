package com.acadev.teamstatsfox.database.entity;

import com.acadev.teamstatsfox.utils.enums.ECardType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "CARDS")
public class Cards {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "MATCH_ID")
	private Long matchId;
	
	@Column(name = "PLAYER_ID")
	private Long playerId;
	
	@Enumerated(EnumType.STRING)
	private ECardType type;
	
	private Integer minute;

}
