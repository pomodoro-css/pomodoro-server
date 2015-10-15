package ch.css.pomodoro.service.dto;

import org.joda.time.DateTime;

public class Tomato {
	private final DateTime startTime;
	private final int tomatoTime;
	private final TomatoTerminationReason timerState;
	private final String taskName;
	
	public Tomato(int tomatoTime, DateTime startTime, TomatoTerminationReason state, String taskName){
		this.tomatoTime = tomatoTime;
		this.startTime = startTime;
		this.timerState = state;
		this.taskName = taskName;
	}
	
	public DateTime getStartTime(){
		return this.startTime;
	}
	
	public TomatoTerminationReason getTimerState(){
		return timerState;
	}

	public int getTomatoTime() {
		return tomatoTime;
	}

	public String getTaskName() {
		return taskName;
	}
}
