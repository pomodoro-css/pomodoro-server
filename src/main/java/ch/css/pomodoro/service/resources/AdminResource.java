package ch.css.pomodoro.service.resources;

import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.css.pomodoro.service.config.AdminManager;
import ch.css.pomodoro.service.config.Config;

@Path("/admin")
@Produces(MediaType.APPLICATION_JSON)
public class AdminResource {

	@GET
	@Path("/config")
	public Config getConfig() {
		return AdminManager.getInstance().getConfig();
	}

	@PUT
	@Path("/timer/start")
	public String startTimer() {
		return AdminManager.getInstance().startTimer();
	}
	
	@PUT
	@Path("/timer/stop")
	public String stopTimer() {
		return AdminManager.getInstance().stopTimer();
	}

}
