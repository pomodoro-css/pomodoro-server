package ch.css.pomodoro.service.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.css.pomodoro.service.dto.UserStatistic;
import ch.css.pomodoro.service.statistic.StatisticManager;

@Path("/statistic")
@Produces(MediaType.APPLICATION_JSON)
public class StatisticResource {

	@GET
	@Path("/biggest")
	public List<UserStatistic> getBiggest() {
		return StatisticManager.getInstance().getBiggestTomatoes();
	}

	@GET
	@Path("/looser")
	public List<UserStatistic> getLooser() {
		return StatisticManager.getInstance().getLooserTomatoes();
	}

	@GET
	@Path("/biggest/{nr}")
	public int getConfig(@PathParam("nr") String nr) {
		return StatisticManager.getInstance().getTotalTime(nr);
	}

}
