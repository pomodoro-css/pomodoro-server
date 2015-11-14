package ch.css.pomodoro.service.dto;

public class UserStatistic implements Cloneable {

	private final String nr;
	private final String name;
	private final Group group;
	private int totalTime;

	public UserStatistic(User user) {
		this.nr = user.getNr();
		this.name = user.getName();
		this.group = user.getGroup();
	}

	public Object clone() throws CloneNotSupportedException {
		UserStatistic clone = (UserStatistic) super.clone();
		return clone;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof UserStatistic)) {
			return false;
		}

		UserStatistic that = (UserStatistic) other;

		// Custom equality check here.
		return this.nr.equals(that.nr) && this.name.equals(that.name) && this.totalTime == that.totalTime;
	}
	
	@Override
	public int hashCode() {
	    int hashCode = 1;

	    hashCode = hashCode * 37 + this.nr.hashCode();
	    hashCode = hashCode * 37 + this.name.hashCode();

	    return hashCode;
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
