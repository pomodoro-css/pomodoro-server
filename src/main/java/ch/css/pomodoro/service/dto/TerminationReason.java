package ch.css.pomodoro.service.dto;

public enum TerminationReason {
	NORMAL(0), USER(1), RUNNING(2);
	
	private final int value;
	
	private TerminationReason(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public boolean isNormal() {
		return this.equals(TerminationReason.NORMAL);
	}

	public boolean isUser() {
		return this.equals(TerminationReason.USER);
	}
	
	public boolean isRunning() {
		return this.equals(TerminationReason.RUNNING);
	}

}
