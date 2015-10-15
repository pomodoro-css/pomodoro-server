package ch.css.pomodoro.service.dto;

import org.joda.time.DateTime;

public class PomodoroTime {
	private DateTime startTime;
	private TimerState timerState;
	
	public PomodoroTime(){
		this.startTime = DateTime.now();
		this.timerState = TimerState.RUNNING;
	}

	public void stop(TimerState value) {
		this.timerState = value;
		
	}
	
	public DateTime getStartTime(){
		return this.startTime;
	}
	
	public TimerState getTimerState(){
		return timerState;
	}
}
