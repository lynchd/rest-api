package ie.dit.users.data.repository;

import ie.dit.users.model.User;

public interface IUserRepository {
	public void saveUser(User user) throws Exception;
	public void updateUser(User user) throws Exception;
	public User findById(String userId);
	public User findByEmail(String email);
}
