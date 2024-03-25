package com.acadev.teamstatsfox.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acadev.teamstatsfox.database.entity.Goals;
import com.acadev.teamstatsfox.database.repository.GoalsRepository;
import com.acadev.teamstatsfox.handler.exception.ApiException;
import com.acadev.teamstatsfox.service.GoalsService;
import com.acadev.teamstatsfox.utils.enums.ApiMessage;

@Service
public class GoalsServiceImpl implements GoalsService {

	@Autowired
	private GoalsRepository repository;

	public Long getNextId() {
		Optional<Goals> entityMaxId = repository.findTopByOrderByIdDesc();
		if (entityMaxId.isPresent())
			return (entityMaxId.get().getId() + 1);
		return 1L;
	}

	public String echo() {
		return "goals echo message";
	}

	public List<Goals> getGoals() {

		List<Goals> goals = repository.findAll();
		if (goals.isEmpty())
			throw new ApiException(ApiMessage.CONTENT_NOT_FOUND);

		return goals;
	}

	public List<Goals> getGoalsByMatchId(Long id) {
		return repository.findAllByMatchId(id);
	}

	public Goals create(Goals request) {
		request.setId(getNextId());
		return repository.save(request);
	}

	public void deleteByMatchId(Long matchId) {
		List<Goals> goalsByMatchId = repository.findAllByMatchId(matchId);
		if (goalsByMatchId.size() > 0) {
			repository.deleteAll(goalsByMatchId);
		}
	}

	public List<Goals> getGoalsByPlayerId(Long id) {
		return repository.findAllByPlayerId(id);
	}

	public List<Goals> getAssistsByPlayerId(Long id) {
		return repository.findAllByAssistPlayerId(id);
	}

	public List<Goals> getGoalsByPlayerIdAndByMatchesIds(Long id, List<Long> matchesIds) {
		return repository.findAllByPlayerIdAndMatchIdIn(id, matchesIds);
	}

	public List<Goals> getGoalsByAssistsPlayerIdAndByMatchesIds(Long id, List<Long> matchesIds) {
		return repository.findAllByAssistPlayerIdAndMatchIdIn(id, matchesIds);
	}

}
