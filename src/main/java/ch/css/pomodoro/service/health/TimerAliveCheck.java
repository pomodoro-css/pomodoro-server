package ch.css.pomodoro.service.health;

import com.codahale.metrics.health.HealthCheck;

import ch.css.pomodoro.service.config.AdminManager;
import ch.css.pomodoro.service.time.ValidationThread;

public class TimerAliveCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {
		ValidationThread validator = AdminManager.getInstance().getValidator();
		if (validator.isAlive()) {
			return Result.healthy("validator alive: ");
		} else {
			return Result.unhealthy("Validator stopped: ");
		}
	}

}
