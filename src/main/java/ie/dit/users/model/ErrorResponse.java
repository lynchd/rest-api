package ie.dit.users.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorResponse
{
	private String errorMessage;
	
	public ErrorResponse() {} 
	
	public ErrorResponse(String message) {
		this.errorMessage = message;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
