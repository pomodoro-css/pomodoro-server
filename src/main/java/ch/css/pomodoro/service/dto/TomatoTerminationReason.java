package ch.css.pomodoro.service.dto;

public enum TomatoTerminationReason {
	TERMINATED_DUE_PROCESS(0), TERMINATED_DUE_USER(1);
	
	private final int value;
	
	private TomatoTerminationReason(int value){
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}

	public boolean isNormalTermination() {
		return this.equals(TomatoTerminationReason.TERMINATED_DUE_PROCESS);
	}

	public boolean isUserTermination() {
		return this.equals(TomatoTerminationReason.TERMINATED_DUE_USER);
	}

}
