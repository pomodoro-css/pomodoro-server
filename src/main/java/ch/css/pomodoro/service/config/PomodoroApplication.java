package ch.css.pomodoro.service.config;

import ch.css.pomodoro.service.resources.UsersResource;
import ch.css.pomodoro.service.time.UserManager;
import ch.css.pomodoro.service.time.ValidationThread;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class PomodoroApplication extends Application<PomodoroConfiguration> {

	public static void main(String[] args) throws Exception {
		new PomodoroApplication().run(args);
	}

	@Override
	public String getName() {
		return "pomodoro is alive...";
	}

	@Override
	public void run(PomodoroConfiguration configuration, Environment environment) throws Exception {
		environment.jersey().register(new UsersResource());

		UserManager manager = UserManager.getInstance();
		ValidationThread validator = new ValidationThread(manager);
		validator.start();
	}
}