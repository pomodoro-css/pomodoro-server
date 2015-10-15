package ch.css.pomodoro.service.dto;

public enum TimerState {
	TERMINATED_DUE_PROCESS(0), TERMINATED_DUE_USER(1), RUNNING(2);
	
	private final int value;
	
	private TimerState(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public boolean isNormalTermination() {
		return this.equals(TimerState.TERMINATED_DUE_PROCESS);
	}

	public boolean isUserTermination() {
		return this.equals(TimerState.TERMINATED_DUE_USER);
	}
	
	public boolean isRunning() {
		return this.equals(TimerState.RUNNING);
	}

}
