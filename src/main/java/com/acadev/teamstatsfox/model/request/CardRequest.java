package com.acadev.teamstatsfox.model.request;

import com.acadev.teamstatsfox.utils.enums.ECardType;

import lombok.Data;

@Data
public class CardRequest {
	private String minute;
	private String playerId;
	private ECardType type;
}
