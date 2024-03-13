package com.acadev.teamstatsfox.model.request;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Opponents;
import com.acadev.teamstatsfox.database.entity.Players;

import lombok.Data;

@Data
public class TournmentDetailsRequest {
	private TournmentRequest tournment;
	private List<Players> players;
	private List<Opponents> opponents;
}