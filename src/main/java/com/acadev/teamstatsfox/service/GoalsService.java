package com.acadev.teamstatsfox.service;

import java.util.List;

import com.acadev.teamstatsfox.database.entity.Goals;

public interface GoalsService {

	String echo();
	
	List<Goals> getGoals();

	List<Goals> getGoalsByMatchId(Long id);
	
	Goals create(Goals request);

	void deleteByMatchId(Long id);

	List<Goals> getGoalsByPlayerId(Long id);

	List<Goals> getAssistsByPlayerId(Long id);

	List<Goals> getGoalsByPlayerIdAndByMatchesIds(Long id, List<Long> matchesIds);

	List<Goals> getGoalsByAssistsPlayerIdAndByMatchesIds(Long id, List<Long> matchesIds);

}
