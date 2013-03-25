package ie.dit.users.exception;

import ie.dit.users.model.ErrorResponse;

import javax.inject.Named;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Named
public class UserExistsExceptionMapper implements ExceptionMapper<UserExistsException>{

	public Response toResponse(UserExistsException arg0) {
		return Response.status(Response.Status.CONFLICT)
				.entity(new ErrorResponse("User with this id already exists.")).build();
	}

}
