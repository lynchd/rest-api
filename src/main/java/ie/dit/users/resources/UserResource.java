package ie.dit.users.resources;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import ie.dit.users.data.repository.IUserRepository;
import ie.dit.users.exception.UserNotFoundException;
import ie.dit.users.model.User;
import ie.dit.users.model.Validation;
import ie.dit.users.utilities.Preconditions;

@Named
@Path("/user")
public class UserResource 
{	
	@Inject
	private IUserRepository userRepository;
	/**
	 * @param authToken 	- Authorization is required
	 * @param userId		- User ID of the user to fetch
	 * @return				- Fetched user 
	 * @throws Exception	- When the user is not found
	 * 						  When there is no valid auth token
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{userId}")
	public User getUser(@HeaderParam("AuthToken") String authToken,
						@PathParam(value = "userId") String userId) throws Exception
	{
		Preconditions.throwIfNull(userId);
		Preconditions.throwNotAuthorizedIfNull(authToken);

		User user = userRepository.findById(userId);
		if(user==null) {
			throw new UserNotFoundException();
		}
		return user;
	}	
	
	/**
	 * @param user			- The new user to create
	 * @return				- HTTP Response only
	 * @throws Exception	- If the user exists, or is otherwise invalid. 
	 */
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createUser(User user) throws Exception
	{
		Validation.throwIfInvalidUser(user);
		
		userRepository.saveUser(user);
		
		return Response.ok().build();
	}	
	
	
	/**
	 * @param authToken		- Authorization token given at login
	 * @param user			- The new User details
	 * @return				- HTTP Response
	 * @throws Exception	- When the updated user information is invalid
	 * 						- When an Authorization token does not resolve to the updated user's id
	 * 						- If the user to update does not exist
	 */
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@HeaderParam("AuthToken") String authToken, 
							   User user) throws Exception
	{
		Preconditions.throwNotAuthorizedIfNull(authToken);
		
		Validation.throwIfInvalidUser(user);
		Validation.throwIfNoUserId(user);
		
		userRepository.updateUser(user);
		
		return Response.ok().build();
	}
	
	/**
	 * 
	 * @param authToken
	 * @return
	 */
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("{userId}")
	public Response deleteUser(@HeaderParam("AuthToken") String authToken,
							   @PathParam("userId") String userId) throws Exception
	{
		Preconditions.throwNotAuthorizedIfNull(authToken);
		Preconditions.throwIfNull(userId);
		
		userRepository.removeUser(userId);
		
		return Response.ok().build();
	}
}
