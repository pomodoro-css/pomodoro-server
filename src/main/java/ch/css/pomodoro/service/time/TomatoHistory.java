package ch.css.pomodoro.service.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ch.css.pomodoro.service.dto.Tomato;
import ch.css.pomodoro.service.dto.TomatoTerminationReason;
import ch.css.pomodoro.service.dto.User;

public class TomatoHistory {

	private HashMap<String, List<Tomato>> history;

	public TomatoHistory() {
		history = new HashMap<>();
	}

	void recordUser(User user, TomatoTerminationReason reason) {
		Tomato tomato = new Tomato(user.getTomatoTime(), user.getStartTime(), reason, user.getTaskName());

		List<Tomato> tomatos = history.get(user.getNr());
		if (tomatos == null) {
			tomatos = new ArrayList<>();
		}

		tomatos.add(tomato);
		history.put(user.getNr(), tomatos);
	}

	List<Tomato> getHistory(String nr) {
		return history.get(nr);
	}

}
