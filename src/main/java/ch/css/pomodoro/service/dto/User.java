package ch.css.pomodoro.service.dto;

import java.util.Vector;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private final String nr;

	private String name;
	private String group;
	private UserState state;
	private int remainingTime;
	private int tomatoTime;
	private Vector<PomodoroTime> pomodoroTimeVector;

	private DateTime startTime;

	public User(String nr) {
		this.nr = nr.toUpperCase();
		state = UserState.OFFLINE;
		remainingTime = 0;
		pomodoroTimeVector = new Vector<PomodoroTime>();
	}

	@JsonProperty
	public String getNr() {
		return nr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public UserState getState() {
		return state;
	}

	public void setState(UserState state) {
		this.state = state;
	}

	public int getRemainingTime() {
		return remainingTime;
	}

	public void setRemainingTime(int remainingTime) {
		this.remainingTime = remainingTime;
	}

	public void setStartTime(DateTime startTime) {
		this.startTime = startTime;
	}

	public DateTime getStartTime() {
		return startTime;
	}

	public boolean hasStartTime() {
		return startTime != null;
	}

	public void setTomatoTime(int time) {
		this.tomatoTime = time;
	}

	public int getTomatoTime() {
		return tomatoTime;
	}

	public void startTimer() {
		pomodoroTimeVector.addElement(new PomodoroTime());
	}

	public void stopTimer(TimerState value) {
		pomodoroTimeVector.lastElement().stop(value);
	}
	
	public Vector<PomodoroTime> getPomodoroTimeVector(){
		return pomodoroTimeVector;
	}

}
