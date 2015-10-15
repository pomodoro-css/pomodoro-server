package ch.css.pomodoro.service.dto;

import org.joda.time.DateTime;

public class Tomato {
	private final DateTime startTime;
	private final int tomatoTime;
	private final TomatoTerminationReason timerState;
	private final String taskName;
	private final int remainingTime;
	private final String userNr;

	public Tomato(User user, TomatoTerminationReason state) {
		this.tomatoTime = user.getTomatoTime();
		this.remainingTime = user.getRemainingTime();
		this.startTime = user.getStartTime();
		this.timerState = state;
		this.taskName = user.getTaskName();
		this.userNr = user.getNr();
	}

	public DateTime getStartTime() {
		return this.startTime;
	}

	public TomatoTerminationReason getTimerState() {
		return timerState;
	}

	public int getTomatoTime() {
		return tomatoTime;
	}

	public String getTaskName() {
		return taskName;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public String getUserNr() {
		return userNr;
	}
}
