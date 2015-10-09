package ch.css.pomodoro.service.time;

import ch.css.pomodoro.service.dto.User;
import ch.css.pomodoro.service.dto.UserState;

public class TimeCalculator {

	static void calculateRemaingingTime(User user, int interval) {
		if (user.getState().isBusy()) {
			int remainingTime = user.getRemainingTime();
			int newRemainingTime = remainingTime * 1000 - interval;
			if (newRemainingTime <= 0) {
				user.setState(UserState.ONLINE);
				user.setRemainingTime(0);
			}
			user.setRemainingTime(newRemainingTime);
		}
	}

}
