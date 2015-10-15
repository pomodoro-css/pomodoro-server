package ch.css.pomodoro.service.time;

import ch.css.pomodoro.service.config.Config;

public class ValidationThread extends Thread {

	private UserManager manager;
	private Config config;

	public ValidationThread(UserManager manager, Config config) {
		this.manager = manager;
		this.config = config;
	}

	public void run() {

		while (!Thread.currentThread().isInterrupted()) {
			manager.validateUsers();
			try {
				Thread.sleep(config.getTimerInMillis());
			} catch (InterruptedException e) {
				throw new RuntimeException(e.getMessage());
			}
		}

	}
}
