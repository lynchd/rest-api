package ie.dit.users.exception;

import ie.dit.users.model.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidRequestExceptionMapper implements ExceptionMapper<InvalidRequestException> {

	public Response toResponse(InvalidRequestException ex) {
		return Response.status(Response.Status.BAD_REQUEST)
				.entity(new ErrorResponse("Request is invalid or malformed.")).build();
	}

}
