package com.acadev.teamstatsfox.model.response;

import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.database.entity.Opponents;
import com.acadev.teamstatsfox.database.entity.Tournments;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MatchesResponse {
	private Matches match;
	private Opponents opponent;
	private Tournments tournment;
}
