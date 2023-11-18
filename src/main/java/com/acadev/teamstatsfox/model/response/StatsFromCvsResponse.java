package com.acadev.teamstatsfox.model.response;

import java.util.ArrayList;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatsFromCvsResponse {
	private Date dateImport;
	private ArrayList<PlayerStatsFromCvs> players;
}
