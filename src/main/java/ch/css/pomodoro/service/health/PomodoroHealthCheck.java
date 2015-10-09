package ch.css.pomodoro.service.health;

import com.codahale.metrics.health.HealthCheck;

public class PomodoroHealthCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {
		return Result.healthy();
	}

}
