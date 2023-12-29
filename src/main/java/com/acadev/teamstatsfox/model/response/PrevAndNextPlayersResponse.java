package com.acadev.teamstatsfox.model.response;

import com.acadev.teamstatsfox.database.entity.Players;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrevAndNextPlayersResponse {
	Players prev;
	Players next;
}
