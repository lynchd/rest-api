package ie.dit.users.exception;

import ie.dit.users.model.ErrorResponse;

import javax.inject.Named;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
@Named
public class UserNotAuthorizedExceptionMapper implements ExceptionMapper<UserNotAuthorizedException>{
	public Response toResponse(UserNotAuthorizedException ex) {
		return Response.status(Response.Status.UNAUTHORIZED)
				.entity(new ErrorResponse("User is not authorized to perform this action.")).build();
	}

}
