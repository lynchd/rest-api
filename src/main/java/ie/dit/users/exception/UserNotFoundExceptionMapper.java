package ie.dit.users.exception;

import ie.dit.users.model.ErrorResponse;

import javax.inject.Named;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Named
public class UserNotFoundExceptionMapper implements ExceptionMapper<UserNotFoundException> {

	public Response toResponse(UserNotFoundException arg0) {
		return Response.status(Response.Status.NOT_FOUND)
				.entity(new ErrorResponse("User with this id does not exist")).build();
	}

}
