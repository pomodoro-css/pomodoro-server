package ch.css.pomodoro.service.config;

import ch.css.pomodoro.service.health.ConfigCheck;
import ch.css.pomodoro.service.health.TimerAliveCheck;
import ch.css.pomodoro.service.resources.AdminResource;
import ch.css.pomodoro.service.resources.BroadcasterResource;
import ch.css.pomodoro.service.resources.GroupResource;
import ch.css.pomodoro.service.resources.HistoryResource;
import ch.css.pomodoro.service.resources.StatisticResource;
import ch.css.pomodoro.service.resources.UsersResource;
import ch.css.pomodoro.service.websocket.BroadcastServlet;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class PomodoroApplication extends Application<PomodoroConfiguration> {

	public static void main(String[] args) throws Exception {
		new PomodoroApplication().run(args);
	}

	@Override
	public void initialize(Bootstrap<PomodoroConfiguration> bootstrap) {
		super.initialize(bootstrap);
		// global libs
		bootstrap.addBundle(new AssetsBundle("/web/lib/", "/lib", null, "lib"));

		// info view
		bootstrap.addBundle(new AssetsBundle("/web/infoview", "/info", "index.html", "infoview"));

		// admin view
		bootstrap.addBundle(new AssetsBundle("/web/adminview/", "/admin", "index.html", "adminview"));
	}

	@Override
	public String getName() {
		return "pomodoro is alive...";
	}

	@Override
	public void run(PomodoroConfiguration configuration, Environment environment) throws Exception {

		environment.jersey().register(new UsersResource());
		environment.jersey().register(new GroupResource());
		environment.jersey().register(new StatisticResource());
		environment.jersey().register(new HistoryResource());
		environment.jersey().register(new AdminResource());

		AdminManager.init();

		// Health Check
		environment.healthChecks().register("config", new ConfigCheck());
		environment.healthChecks().register("thread", new TimerAliveCheck());

		// WebSocket
		environment.jersey().register(new BroadcasterResource(environment.getObjectMapper()));
		environment.getApplicationContext().getServletHandler().addServletWithMapping(BroadcastServlet.class, "/ws/*");

	}
}