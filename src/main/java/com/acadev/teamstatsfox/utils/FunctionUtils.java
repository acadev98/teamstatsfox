package com.acadev.teamstatsfox.utils;

import java.util.ArrayList;

import com.acadev.teamstatsfox.model.response.PlayerStatsFromCvs;

public class FunctionUtils {

	public static ArrayList<PlayerStatsFromCvs> generateArrayListOfPlayerStatsFromCvs() {

		ArrayList<PlayerStatsFromCvs> arrayList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			arrayList.add(PlayerStatsFromCvs.builder().name("PLAYERMOCK" + i).matches(i).goals(i).assists(i).redCard(i)
					.yellowCard(i).captains(i).build());
		}

		return arrayList;
	}

}
