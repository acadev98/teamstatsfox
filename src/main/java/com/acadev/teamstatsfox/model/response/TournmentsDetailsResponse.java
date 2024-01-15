package com.acadev.teamstatsfox.model.response;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Players;
import com.acadev.teamstatsfox.database.entity.Tournment;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TournmentsDetailsResponse {
	
	private Tournment tournment;
	private List<Players> players;

}
