package ch.css.pomodoro.service.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import ch.css.pomodoro.service.dto.Group;
import ch.css.pomodoro.service.time.UserManager;

@Path("/groups")
@Produces(MediaType.APPLICATION_JSON)
public class GroupResource {

	@GET
	public List<Group> getConfig() {
		return UserManager.getInstance().getGroups();
	}

}
