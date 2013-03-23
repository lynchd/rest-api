package ie.dit.users.data.repository;

import ie.dit.users.model.Login;
import ie.dit.users.model.LoginResponse;

public interface ILoginRepository 
{
	public LoginResponse login(Login login);
	public LoginResponse findByToken(String token);
}
