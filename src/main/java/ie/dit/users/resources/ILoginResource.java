package ie.dit.users.resources;

import ie.dit.users.model.Login;
import ie.dit.users.model.LoginResponse;

public interface ILoginResource 
{
	/**
	 * Given a set of login credentials, validate a user.
	 * @param login
	 * @return LoginResponse
	 * @throws Exception
	 */
	public LoginResponse login(Login login) throws Exception;
}
