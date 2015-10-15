package ch.css.pomodoro.service.dto;

public class UserStatistic {

	private final String nr;
	private int totalTime;

	public UserStatistic(String nr) {
		this.nr = nr;
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

}
