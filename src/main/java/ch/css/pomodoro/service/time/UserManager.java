package ch.css.pomodoro.service.time;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import ch.css.pomodoro.service.dto.Group;
import ch.css.pomodoro.service.dto.Tomato;
import ch.css.pomodoro.service.dto.TomatoTerminationReason;
import ch.css.pomodoro.service.dto.User;
import ch.css.pomodoro.service.dto.UserState;
import ch.css.pomodoro.service.statistic.StatisticManager;
import ch.css.pomodoro.service.websocket.BroadcastSocket;

public class UserManager {

	private static UserManager instance;
	private UserRepository repo;
	private TomatoHistory history;

	private UserManager() {
		repo = new UserRepository();
		history = new TomatoHistory();
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

	public void validateUsers() {
		DateTime now = new DateTime();
		for (User user : getUsers()) {
			if (user.getState().isBusy() && user.getStartTime() != null) {
				long remainingTimeInMillis = TimeCalculator.calculate(user, now);

				// record user before stop
				StatisticManager.getInstance().record(user, user.getRemainingTime(),
						(int) remainingTimeInMillis / 1000);

				if (remainingTimeInMillis <= 0) {
					stop(user.getNr(), TomatoTerminationReason.TERMINATED_DUE_PROCESS);
				} else {
					int remainingTimeInSeconds = Long.valueOf(remainingTimeInMillis).intValue();
					remainingTimeInSeconds /= 1000;
					user.setRemainingTime(remainingTimeInSeconds);
				}
			}
		}

	}

	public void start(String nr, int tomatotime, String taskName) {
		User user = repo.get(nr);
		user.setState(UserState.BUSY);
		user.setTomatoTime(tomatotime);
		user.setRemainingTime(tomatotime);
		user.setTaskName(taskName);
		user.setStartTime(DateTime.now());
		
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		try {
			String json = ow.writeValueAsString(user);
			BroadcastSocket.broadcast(json);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

	public void stop(String nr, TomatoTerminationReason reason) {
		User user = repo.get(nr);
		if (user != null && user.getState().isBusy()) {
			history.recordUser(user, reason);

			user.setState(UserState.ONLINE);
			user.setRemainingTime(0);
			user.setStartTime(null);
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			try {
				String json = ow.writeValueAsString(user);
				BroadcastSocket.broadcast(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}

	public void setOffline(String nr) {
		User user = getUser(nr);
		if (user != null) {
			stop(nr, TomatoTerminationReason.TERMINATED_DUE_USER);
			user.setState(UserState.OFFLINE);
			
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			try {
				String json = ow.writeValueAsString(user);
				BroadcastSocket.broadcast(json);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}

	}

	public List<Group> getGroups() {
		List<Group> groups = new ArrayList<>();
		for (User user : getUsers()) {
			groups.add(user.getGroup());
		}
		return groups;
	}

	public List<Tomato> getHistory(String nr) {
		return history.getHistory(nr);
	}

}
