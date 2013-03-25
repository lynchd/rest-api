package ie.dit.users.resources;

import ie.dit.users.data.repository.ILoginRepository;
import ie.dit.users.model.Login;
import ie.dit.users.model.LoginResponse;
import ie.dit.users.utilities.Preconditions;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@Path("/login")
public class LoginResource 
{
	@Inject
	private ILoginRepository loginRepository;
	/**
	 * @param login			- Credentials
	 * @return				- Session details
	 * @throws Exception	- When authorization credentials are not valid
	 * 						- When request is invalid
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public LoginResponse login(Login login) throws Exception
	{
		Preconditions.throwIfNull(login);
		return loginRepository.login(login);
	}
}
