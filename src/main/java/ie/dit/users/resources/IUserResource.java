package ie.dit.users.resources;

import ie.dit.users.model.User;
import javax.ws.rs.core.Response;

public interface IUserResource 
{
	public User getUser(String authToken,
						String userId) throws Exception;
	
	public Response createUser(User user) throws Exception;
	
	public Response updateUser(String authToken, 
			  				   User user) throws Exception;
			   
    public Response deleteUser(String authToken,
    						   String userId) throws Exception;

}
