package ch.css.pomodoro.service.config;

public class Config {

	private int timerInMillis;
	public static final int defaultTomatoTimeInSeconds = 1500;

	public Config() {
		this.timerInMillis = 1000; // 1 second
	}

	public int getTimerInMillis() {
		return timerInMillis;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Timmer in Millis: ").append(this.timerInMillis);
		return builder.toString();
	}

}
