package ch.css.pomodoro.service.time;

import java.util.List;

import org.joda.time.DateTime;

import ch.css.pomodoro.service.dto.User;
import ch.css.pomodoro.service.dto.UserState;

public class UserManager {

	private static UserManager instance;
	private UserRepository repo;

	private UserManager() {
		repo = new UserRepository();
	}

	public static UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}

	public boolean addUser(User user) {
		if (user.getNr() == null || user.getName().length() < 4) {
			throw new RuntimeException("Number is required for User");
		}
		return repo.add(user);
	}

	public boolean removeUser(String nr) {
		return repo.remove(nr);
	}

	public List<User> getUsers() {
		return repo.getAll();
	}

	public User getUser(String nr) {
		return repo.get(nr);
	}

	public UserState getUserState(String nr) {
		User user = repo.get(nr);
		if (user != null) {
			return user.getState();
		}
		return null;
	}

	public void validateUsers(int interval) {
		TimeCalculator.calculateRemainingTime(getUsers());
	}

	public void start(String nr, int tomatotime) {
		User user = repo.get(nr);
		user.setState(UserState.BUSY);
		user.setTomatoTime(tomatotime);
		user.setRemainingTime(tomatotime);
		user.setStartTime(DateTime.now());
	}

	public void stop(String nr) {
		User user = repo.get(nr);
		user.setState(UserState.OFFLINE);
		user.setRemainingTime(0);
		user.setStartTime(null);
	}

}
