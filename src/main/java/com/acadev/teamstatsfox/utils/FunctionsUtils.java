package com.acadev.teamstatsfox.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.acadev.teamstatsfox.model.request.GoalRequest;
import com.acadev.teamstatsfox.model.response.PlayerStatsFromCvs;

public class FunctionsUtils {

	public static ArrayList<PlayerStatsFromCvs> generateArrayListOfPlayerStatsFromCvs() {

		ArrayList<PlayerStatsFromCvs> arrayList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			arrayList.add(PlayerStatsFromCvs.builder().name("PLAYERMOCK" + i).matches(i).goals(i).assists(i).redCard(i)
					.yellowCard(i).captains(i).build());
		}

		return arrayList;
	}
	
	public static LocalDateTime generateLocalDateTimeFromLocalDateAndTimeString(LocalDate localDate, String timeString) {
		
		String localDateString = localDate.toString();
		String localDateTimeString = localDateString+" "+timeString;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeString, formatter);
		
		return localDateTime;
	}

	public static Integer calculateOurGoals(List<GoalRequest> goalsRequest) {
		Integer goalsOurCount = (int) goalsRequest
				  .stream()
				  .filter(c -> c.getOur())
				  .count();
		
		return goalsOurCount;
	}

}
