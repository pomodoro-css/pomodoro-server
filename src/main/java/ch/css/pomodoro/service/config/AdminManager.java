package ch.css.pomodoro.service.config;

import ch.css.pomodoro.service.time.UserManager;
import ch.css.pomodoro.service.time.ValidationThread;

public class AdminManager {

	private static AdminManager instance;

	private UserManager usermanager;
	private ValidationThread validator;
	private Config config;

	private AdminManager() {
		usermanager = UserManager.getInstance();
		config = new Config();
		startTimer();
	}

	public static void init() {
		instance = new AdminManager();
	}

	public static AdminManager getInstance() {
		return instance;
	}

	public Config getConfig() {
		return config;
	}

	public ValidationThread getValidator() {
		return validator;
	}

	public String startTimer() {
		validator = new ValidationThread(usermanager, config);
		validator.start();

		return "timer successfull started";
	}

	public String stopTimer() {
		validator.interrupt();

		int tries = 0;
		while (tries < 5 && validator.isAlive()) {
			tries++;
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		return validator.isAlive() ? "timer could not be stopped" : "timer stopped";
	}

}
