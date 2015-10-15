package ch.css.pomodoro.service.dto;

public class UserStatistic {

	private final String nr;
	private final String name;
	private final Group group;
	private int totalTime;

	public UserStatistic(User user) {
		this.nr = user.getNr();
		this.name = user.getName();
		this.group = user.getGroup();
	}

	public String getUserNr() {
		return nr;
	}

	public void setTotalTime(int seconds) {
		totalTime = seconds;
	}

	public int getTotalTime() {
		return totalTime;
	}

	public String getName() {
		return name;
	}

	public Group getGroup() {
		return group;
	}

}
