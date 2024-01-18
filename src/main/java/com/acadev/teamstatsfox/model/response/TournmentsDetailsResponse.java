package com.acadev.teamstatsfox.model.response;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Opponents;
import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.database.entity.Tournments;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournmentsDetailsResponse {
	
	private Tournments tournment;
	private List<Players> players;
	private List<Opponents> opponents;

}
