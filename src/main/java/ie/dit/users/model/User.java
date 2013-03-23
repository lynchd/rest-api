package ie.dit.users.model;


import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnore;

@XmlRootElement
public class User 
{
	private String name;
	private String email;
	private String id;
	private String password;
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}	
	@JsonIgnore
	public static User getStockUser() 
	{
		User user = new User();
		user.setPassword("test");
		user.setId("1");
		user.setName("Stock Test User");
		user.setEmail("stock@testuser.com");
		return user;
	}
	@JsonIgnore
	public Login getLogin() 
	{
		Login login = new Login();
		login.setEmail(email);
		login.setPassword(password);
		return login;
	}
	public boolean areEqual(User otherUser)
	{
		if(otherUser == null)
			return false;
		return email.equals(otherUser.getEmail()) &&
			   id.equals(otherUser.getId());
	}
}
