package com.acadev.teamstatsfox.service;

import java.util.ArrayList;

import com.acadev.teamstatsfox.model.response.PlayerStatsFromCvs;

public interface ReadCvsService {

	ArrayList<PlayerStatsFromCvs> getStatsPlayers();

}
