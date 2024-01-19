package com.acadev.teamstatsfox.model.response;

import com.acadev.teamstatsfox.database.entity.Opponents;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrevAndNextOpponentsResponse {
	Opponents prev;
	Opponents next;
}
