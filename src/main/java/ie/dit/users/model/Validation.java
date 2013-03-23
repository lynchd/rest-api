package ie.dit.users.model;

import ie.dit.users.exception.InvalidRequestException;

public class Validation 
{
	public static void throwIfInvalidUser(User user) {
		if(user==null) { throw new InvalidRequestException(); }
		if(user.getName()==null || user.getEmail()==null)
			throw new InvalidRequestException();
	}
	
	public static void throwIfNoUserId(User user) {
		if(user==null || user.getId()==null) {
			throw new InvalidRequestException();
		}
	}
}
