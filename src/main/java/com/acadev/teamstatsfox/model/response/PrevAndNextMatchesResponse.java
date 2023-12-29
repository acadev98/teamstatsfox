package com.acadev.teamstatsfox.model.response;

import com.acadev.teamstatsfox.database.entity.Matches;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrevAndNextMatchesResponse {
	Matches prev;
	Matches next;
}
