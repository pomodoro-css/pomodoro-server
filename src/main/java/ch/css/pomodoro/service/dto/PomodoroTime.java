package ch.css.pomodoro.service.dto;

import org.joda.time.DateTime;

public class PomodoroTime {
	private DateTime startTime;
	private TerminationReason terminationDue;
	
	public PomodoroTime(){
		this.startTime = DateTime.now();
		this.terminationDue = TerminationReason.RUNNING;
	}

	public void stop(TerminationReason value) {
		this.terminationDue = value;
		
	}
	
	public DateTime getStartTime(){
		return this.startTime;
	}
	
	public TerminationReason getTerminationDue(){
		return terminationDue;
	}
}
