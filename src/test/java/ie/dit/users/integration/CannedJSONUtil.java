package ie.dit.users.integration;

import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class CannedJSONUtil {
	public static final String CREATE_USER 				= "/create-user.json";
	public static final String UPDATE_USER 				= "/update-user.json";
	public static final String INVALID_LOGIN 			= "/invalid-login.json";
	public static final String LOGIN 					= "/login.json";
	public static final String CREATE_USER_MALFORMED 	= "/malformed-create-user.json";
	
	public static String loadJSON(String filename) 
		throws Exception
	{
		CannedJSONUtil util = new CannedJSONUtil();
		return util.loadCannedJSON(filename);
	}
	
	public String loadCannedJSON(String filename)
			throws Exception
	{
		InputStream in = null;
		try {
			in = getClass().getResourceAsStream(filename);
			return IOUtils.toString(in); 
		}
		finally {
			if (in!=null)
			 IOUtils.closeQuietly(in);
		}
	}
}
