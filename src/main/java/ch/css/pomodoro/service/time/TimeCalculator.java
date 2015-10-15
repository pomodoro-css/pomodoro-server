package ch.css.pomodoro.service.time;

import org.joda.time.DateTime;

import ch.css.pomodoro.service.dto.User;

public class TimeCalculator {


	 static long calculate(User user, DateTime now) {
		long startMiliis = user.getStartTime().getMillis();
		long nowMillis = now.getMillis();

		long usedTimeMillis = nowMillis - startMiliis;

		long remainingTimeInMillis = (user.getTomatoTime() * 1000) - usedTimeMillis;
		return remainingTimeInMillis;
	}

}
