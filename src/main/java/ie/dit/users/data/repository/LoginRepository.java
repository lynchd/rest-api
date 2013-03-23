package ie.dit.users.data.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

import ie.dit.users.exception.UserNotAuthorizedException;
import ie.dit.users.model.Login;
import ie.dit.users.model.LoginResponse;
import ie.dit.users.model.User;

public class LoginRepository implements ILoginRepository
{
	private HashMap<String, LoginResponse> sessions;
	
	private static LoginRepository INSTANCE = new LoginRepository();
	
	private LoginRepository() {
		sessions = new HashMap<String, LoginResponse>();
	}
	
	public static LoginRepository getInstance() {
		return INSTANCE;
	}
	
	public LoginResponse login(Login login) {
		UserRepository userRepo = UserRepository.getInstance();
		User user = userRepo.findByEmail(login.getEmail());
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
