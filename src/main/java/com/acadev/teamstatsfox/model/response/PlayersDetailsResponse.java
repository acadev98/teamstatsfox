package com.acadev.teamstatsfox.model.response;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Cards;
import com.acadev.teamstatsfox.database.entity.Goals;
import com.acadev.teamstatsfox.database.entity.Matches;
import com.acadev.teamstatsfox.database.entity.Players;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayersDetailsResponse {
	private Players player;
	private List<Goals> goals;
	private List<Goals> assists;
	private List<Matches> matches;
	private List<Cards> cards;
}
