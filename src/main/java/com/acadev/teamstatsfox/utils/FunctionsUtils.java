package com.acadev.teamstatsfox.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.acadev.teamstatsfox.model.request.GoalRequest;

public class FunctionsUtils {
	
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
