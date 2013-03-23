package ie.dit.users.integration;

import java.io.File;
import java.io.StringReader;
import java.io.StringWriter;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;


public class JSON {
	public static String marshallToString(Object object) 
	{
		if(object==null) {
			return "{}";
		}
		try {
		ObjectMapper mapper = getConfiguredObjectMapper();
		StringWriter writer = new StringWriter();
		mapper.writeValue(writer, object);
		return writer.toString();
		}
		catch(Exception ex) { return null; }
	}
	
	public static Object unmarshall(String string, Class<?> cls) 
		throws Exception
	{
		ObjectMapper mapper = getConfiguredObjectMapper();
		StringReader reader = new StringReader(string);
		return mapper.readValue(reader, cls);
	}
	
	public static ObjectMapper getConfiguredObjectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
		mapper.configure(SerializationConfig.Feature.SORT_PROPERTIES_ALPHABETICALLY, true);
		mapper.configure(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS, true);
		mapper.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
		mapper.configure(SerializationConfig.Feature.WRAP_EXCEPTIONS, true);
		mapper.configure(SerializationConfig.Feature.WRITE_EMPTY_JSON_ARRAYS, true);
		mapper.setSerializationInclusion(Inclusion.NON_NULL);
		return mapper;
	}
		
	public static Object unmarshall(File file, Class<?> clazz)
		throws Exception
	{
		ObjectMapper mapper = new ObjectMapper();
		return mapper.readValue(file, clazz);
	}
}
