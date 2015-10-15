package ch.css.pomodoro.service.health;

import com.codahale.metrics.health.HealthCheck;

import ch.css.pomodoro.service.config.AdminManager;
import ch.css.pomodoro.service.config.Config;

public class ConfigCheck extends HealthCheck {

	@Override
	protected Result check() throws Exception {
		Config config = AdminManager.getInstance().getConfig();
		if (config.getTimerInMillis() >= 1000) {
			return Result.healthy("config ok: " + config);
		} else {
			return Result.unhealthy("config nok: " + config);
		}
	}

}
