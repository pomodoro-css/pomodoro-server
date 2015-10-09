package ch.css.pomodoro.service.config;

import com.codahale.metrics.health.HealthCheck;

public class PomodoroHealtCheck extends HealthCheck {
	
	private final String template;

	public PomodoroHealtCheck(String template) {
        this.template = template;
    }

	@Override
	protected Result check() throws Exception {
		final String saying = String.format(template, "TEST");
		if (!saying.contains("TEST")) {
			return Result.unhealthy("template doesn't include a name");
		}
		return Result.healthy();
	}

}
