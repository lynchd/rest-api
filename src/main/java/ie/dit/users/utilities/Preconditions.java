package ie.dit.users.utilities;

import ie.dit.users.data.repository.LoginRepository;
import ie.dit.users.exception.InvalidRequestException;
import ie.dit.users.exception.UserNotAuthorizedException;
import ie.dit.users.model.LoginResponse;

public class Preconditions 
{
	public static void throwIfNull(Object object)
	{
		if(object==null)
			throw new InvalidRequestException();
	}
	
	public static void throwNotAuthorizedIfNull(String token) {
		if(token==null) {
			throw new UserNotAuthorizedException();
		}
		LoginResponse session = LoginRepository.getInstance().findByToken(token);
		if(session==null) {
			throw new UserNotAuthorizedException();
		}
	}

}
