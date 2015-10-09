package ch.css.pomodoro.service.time;

import java.util.List;

import org.joda.time.DateTime;

import ch.css.pomodoro.service.dto.User;
import ch.css.pomodoro.service.dto.UserState;

public class TimeCalculator {

	private static int TOMATO_TIME_MILLIS = 1500000; // 25min

	static void calculateRemaingingTime(List<User> users) {
		DateTime now = new DateTime();
		for (User user : users) {
			if (user.getState().isBusy() && user.getStartTime() != null) {
				calculate(user, now);
			}
		}
	}

	private static void calculate(User user, DateTime now) {
		long startMiliis = user.getStartTime().getMillis();
		long nowMillis = now.getMillis();

		long usedTimeMillis = nowMillis - startMiliis;

		long remainingTimeInMillis = TOMATO_TIME_MILLIS - usedTimeMillis;
		if (remainingTimeInMillis <= 0) {
			user.setState(UserState.ONLINE);
			user.setRemainingTime(0);
		} else {
			int remainingTimeInSeconds = Long.valueOf(remainingTimeInMillis).intValue();
			remainingTimeInSeconds /= 1000;
			user.setRemainingTime(remainingTimeInSeconds);
		}
	}

}
