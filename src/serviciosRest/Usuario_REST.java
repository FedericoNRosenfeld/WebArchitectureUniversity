package serviciosRest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import entidades.Usuario;
import entities.User;
import services.DAOUser;

@Path("/usuarios")
public class Usuario_REST {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		List<User> result = DAOUsuario.getInstance().getUsuarios();
		return result;
	}
}
