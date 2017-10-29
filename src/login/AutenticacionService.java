package login;

public class AutenticacionService {
	@POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response autenticarUser(Credencial credentials){..}
    private void autenticar(String username, String password) throws Exception{..}
    private String emitirToken(String username){..}	

