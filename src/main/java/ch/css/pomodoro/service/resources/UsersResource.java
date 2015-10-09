package ch.css.pomodoro.service.resources;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import ch.css.pomodoro.service.dto.User;
import ch.css.pomodoro.service.dto.UserState;
import ch.css.pomodoro.service.time.UserManager;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UsersResource {

	@POST
	public boolean addUser(@QueryParam("nr") String nr, @QueryParam("name") String name,
			@QueryParam("group") String group) {

		if (nr == null || nr.length() < 4 || name == null) {
			return false;
		}

		User user = new User(nr);
		user.setName(name);
		user.setGroup(group);
		user.setState(UserState.ONLINE);

		return UserManager.getInstance().addUser(user);
	}

	@GET
	public List<User> getAll() {
		return UserManager.getInstance().getUsers();
	}

	@GET
	@Path("/{nr}/State")
	public UserState getState(@PathParam("nr") String nr) {
		return UserManager.getInstance().getUserState(nr);
	}

	@DELETE
	@Path("/{nr}")
	public boolean deleteUser(@QueryParam("nr") String nr) {
		return !UserManager.getInstance().removeUser(nr);
	}

}
