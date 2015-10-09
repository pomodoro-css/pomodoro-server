package ch.css.pomodoro.service.time;

public class ValidationThread extends Thread {

	private UserManager manager;
	private static int interval = 5000; // 5 seconds

	public ValidationThread(UserManager manager) {
		this.manager = manager;
	}

	public void run() {

		while (true) {
			manager.validateUsers(interval);
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

	}
}
