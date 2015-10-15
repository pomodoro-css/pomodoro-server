package ch.css.pomodoro.service.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ch.css.pomodoro.service.config.Config;
import ch.css.pomodoro.service.dto.TomatoTerminationReason;
import ch.css.pomodoro.service.dto.User;
import ch.css.pomodoro.service.dto.UserState;
import ch.css.pomodoro.service.time.UserManager;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

	@GET
	public List<User> getAll() {
		return UserManager.getInstance().getUsers();
	}

	@GET
	@Path("/{nr}")
	public User getState(@PathParam("nr") String nr) {
		return UserManager.getInstance().getUser(nr);
	}
	
	@PUT
	@Path("/{nr}/offline")
	public Response start(@PathParam("nr") String nr) {
		UserManager.getInstance().setOffline(nr);
		return Response.ok().build();
	}


	@PUT
	@Path("/{nr}/start")
	public Response start(@PathParam("nr") String nr, @QueryParam("tomatotime") int tomatotime, @QueryParam("taskname") String taskName) {
		if (tomatotime == 0) {
			tomatotime = Config.defaultTomatoTimeInSeconds;
		}
		UserManager.getInstance().start(nr, tomatotime, taskName );
		return Response.ok().build();
	}

	@PUT
	@Path("/{nr}/stop")
	public Response stop(@PathParam("nr") String nr) {
		UserManager.getInstance().stop(nr, TomatoTerminationReason.TERMINATED_DUE_USER);
		return Response.ok().build();
	}

	@POST
	public Response addUser(@QueryParam("nr") String nr, @QueryParam("name") String name,
			@QueryParam("group") String group) {

		if (nr == null || nr.length() < 4 || name == null) {
			return Response.serverError()
					.entity("adding user requires a number and a name (optional a group) like users?nr=1234&name=muster&group=teamA")
					.build();
		}

		User user = new User(nr);
		user.setName(name);
		user.setGroup(group);
		user.setState(UserState.ONLINE);

		if (UserManager.getInstance().addUser(user)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}

	@DELETE
	@Path("/{nr}")
	public Response deleteUser(@PathParam("nr") String nr) {
		if (UserManager.getInstance().removeUser(nr)) {
			return Response.ok().build();
		} else {
			return Response.notModified().build();
		}
	}

}
