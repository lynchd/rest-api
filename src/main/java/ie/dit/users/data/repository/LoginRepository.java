package ie.dit.users.data.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import ie.dit.users.exception.UserNotAuthorizedException;
import ie.dit.users.model.Login;
import ie.dit.users.model.LoginResponse;
import ie.dit.users.model.User;

@Named
public class LoginRepository implements ILoginRepository
{
	@Inject
	private IUserRepository userRepository;
	
	private HashMap<String, LoginResponse> sessions;
	
	private LoginRepository() {
		sessions = new HashMap<String, LoginResponse>();
	}

	public LoginResponse login(Login login) {
		User user = userRepository.findByEmail(login.getEmail());
		if(user==null || !login.getPassword().equals(user.getPassword())) {
			throw new UserNotAuthorizedException();
		}	
		LoginResponse response = new LoginResponse();
		String salt = login.getEmail() + new Date().toString();
		response.setAuthToken(UUID.nameUUIDFromBytes(salt.getBytes()).toString());
		response.setUserId(user.getId());
		sessions.put(response.getAuthToken(), response);
		return response;
	}

	public LoginResponse findByToken(String token) {
		return sessions.get(token);
	}
	
	public void throwIfNotUserToken(String token, String userId) {
		LoginResponse response = sessions.get(token);
		if(!response.getUserId().equals(userId))
			throw new UserNotAuthorizedException();
	}
}
