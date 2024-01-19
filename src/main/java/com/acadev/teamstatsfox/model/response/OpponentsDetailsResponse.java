package com.acadev.teamstatsfox.model.response;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.database.entity.Opponents;
import com.acadev.teamstatsfox.database.entity.Tournments;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OpponentsDetailsResponse {
	private Opponents opponent;
	private List<Matches> matches;
	private List<Tournments> tournments;
}
