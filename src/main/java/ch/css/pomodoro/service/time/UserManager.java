package ch.css.pomodoro.service.time;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

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
	}

	public void stop(String nr, TomatoTerminationReason reason) {
		User user = repo.get(nr);
		if (user != null && user.getState().isBusy()) {
			history.recordUser(user, reason);

			user.setState(UserState.ONLINE);
			user.setRemainingTime(0);
			user.setStartTime(null);

			BroadcastSocket.broadcast(getJSonObject("online", user));
		}
	}

	public void setOffline(String nr) {
		User user = getUser(nr);
		if (user != null) {
			stop(nr, TomatoTerminationReason.TERMINATED_DUE_USER);
			user.setState(UserState.OFFLINE);

			BroadcastSocket.broadcast(getJSonObject("offline", user));

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

	private String getJSonObject(String method, Object object) {
		Map<String, Object> theMap = new LinkedHashMap<>();

		// put your objects in the Map with their names as keys
		theMap.put("method", method);
		theMap.put("object", object);

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// configure Object mapper for pretty print
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		// writing to console, can write to any output stream such as file
		StringWriter jsonString = new StringWriter();
		try {
			objectMapper.writeValue(jsonString, theMap);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonString.toString();
	}

}
