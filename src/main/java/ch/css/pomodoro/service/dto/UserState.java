package ch.css.pomodoro.service.dto;

public enum UserState {

	OFFLINE(0), ONLINE(1), BUSY(2);

	private final int value;

	private UserState(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public boolean isOffline() {
		return this.equals(UserState.OFFLINE);
	}

	public boolean isOnline() {
		return this.equals(UserState.ONLINE);
	}

	public boolean isBusy() {
		return this.equals(UserState.BUSY);
	}

}
