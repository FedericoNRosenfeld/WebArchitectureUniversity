package serviciosRest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import entidades.Usuario;
import servicios.DAOUsuario;


@Path("/usuarios")
public class Usuario_REST {
	
	// TODOS LOS USUARIOS
	@GET
	@Secured
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getUsers() {
		List<User> result = DAOUsuario.getInstance().getUsuarios();
		return result;
	}

	 // CREAR UN USUARIO
	@POST
	@Secured
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearusuario(Usuario usuario) {
	Usuario usuario= DAOUsuario.getInstance().crearusuario(usuario.getNombre(),usuario.getApellido(),usuario.getUserName(),usuario.getPassword());
	if(usuario!=null) {
		return Response.status(201).entity(usuario).build();
	}
	}
	// TRAE A UN USUARIO EN BASE A SU ID
	@GET
	@Secured
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario getUsuario(@PathParam("id") String id) {
	 	int idusuario = Integer.valueOf(id);
	 	Usuario usuario = DAOUsuario.getInstance().getUsuario(idusuario);
	 	if(usuario!=null)
			return usuario;
		else
			throw new NoExiste(id);
	}
	
	// MODIFICA A UN USUARIO EN BASE A SU ID

	@PUT
	@Secured
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("id") int id,Usuario user) {
		Usuario result = UsuarioDAO.getInstance().update(id, user);
		if(result!=null) return Response.status(201).entity(result).build();
		throw new NoExiste(id);
	}
	
	// BORRA A UN USUARIO EN BASE A SU ID

	@DELETE
	@Secured
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuario(@PathParam("id") int id) {
	boolean resultado= DAOUsuario.getInstance().deleteUsuario(id);
	if(resultado) {
		return Response.status(201).build();
	} 
	else {
		throw new NoExiste(id);
	}	
	}
	
	/// Para los mensajes de RecursoNoExiste
	
	public class NoExiste extends WebApplicationException {
	//  https://stackoverflow.com/questions/583973/jax-rs-jersey-how-to-customize-error-handling
	     public NoExiste(int id) {
	         super(Response.status(Response.Status.NOT_FOUND)
	             .entity("El recurso con id "+id+" no fue encontrado").type(MediaType.TEXT_PLAIN).build());
	     }
	}

	
	
	
	
	
	
	
	
	
}

