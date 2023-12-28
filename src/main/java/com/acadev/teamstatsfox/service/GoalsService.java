package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Goals;

public interface GoalsService {

	String echo();
	
	List<Goals> getGoals();

	List<Goals> getGoalsByMatchId(Long id);
	
	Goals create(Goals request);

}
