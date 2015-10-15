package ch.css.pomodoro.service.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.css.pomodoro.service.dto.Tomato;
import ch.css.pomodoro.service.time.UserManager;

@Path("/history")
@Produces(MediaType.APPLICATION_JSON)
public class HistoryResource {

	@GET
	@Path("/{nr}")
	public List<Tomato> getState(@PathParam("nr") String nr) {
		return UserManager.getInstance().getHistory(nr);
	}

}
