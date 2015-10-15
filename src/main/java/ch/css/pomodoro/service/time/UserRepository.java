package ch.css.pomodoro.service.time;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ch.css.pomodoro.service.dto.User;

public class UserRepository {

	private Map<String, User> users;

	UserRepository() {
		users = new HashMap<>();
	}

	boolean add(User user) {
		if (!users.containsKey(user.getNr())) {
			users.put(user.getNr(), user);
			return true;
		}
		return false;
	}

	boolean remove(String nr) {
		User removed = users.remove(nr);
		return removed != null;
	}

	User get(String nr) {
		return users.get(nr.toUpperCase());
	}

	List<User> getAll() {
		return new ArrayList<User>(users.values());
	}

}
