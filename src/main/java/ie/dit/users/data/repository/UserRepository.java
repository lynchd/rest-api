package ie.dit.users.data.repository;

import java.util.HashMap;

import javax.inject.Named;

import ie.dit.users.exception.UserExistsException;
import ie.dit.users.exception.UserNotFoundException;
import ie.dit.users.model.User;

@Named
public class UserRepository implements IUserRepository
{
	private HashMap<String, User> users;
	
	private UserRepository() { 
		users = new HashMap<String, User>();
		User seedUser = User.getStockUser();
		users.put(seedUser.getId(), seedUser);	
	};
	
	public void saveUser(User user) throws Exception {
		if(users.containsKey(user.getId()))
			throw new UserExistsException();
		users.put(user.getId(), user);		
	}

	public void updateUser(User user) throws Exception {
		String key = user.getId();
		if(!users.containsKey(key))
			throw new UserNotFoundException();
		removeUser(user.getId());
		saveUser(user);
	}
	
	public void removeUser(String userId) throws Exception {
		if(!users.containsKey(userId))
			throw new UserNotFoundException();
		users.remove(userId);
	}

	public User findById(String userId) {
		return users.get(userId);
	}

	public User findByEmail(String email) {
		for(User user : users.values()) {
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}
}
