package ch.css.pomodoro.service.dto;

import org.joda.time.DateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {

	private final String nr;

	private String name;
	private Group group;
	private UserState state;
	private int remainingTime;
	private int tomatoTime;

	private DateTime startTime;

	private String taskName;

	public User(String nr) {
		this.nr = nr.toUpperCase();
		state = UserState.OFFLINE;
		remainingTime = 0;
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

	public Group getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = new Group(group);
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

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskName() {
		return taskName;
	}

}
