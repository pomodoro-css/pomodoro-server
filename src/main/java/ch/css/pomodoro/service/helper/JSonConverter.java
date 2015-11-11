package ch.css.pomodoro.service.helper;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JSonConverter {

	public static String getJSonObject(String method, Object object) {
		Map<String, Object> theMap = new LinkedHashMap<>();

		// put your objects in the Map with their names as keys
		theMap.put("method", method);
		theMap.put("object", object);

		// create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();

		// configure Object mapper for pretty print
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);

		// writing to console, can write to any output stream such as file
		StringWriter jsonString = new StringWriter();
		try {
			objectMapper.writeValue(jsonString, theMap);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return jsonString.toString();
	}

}
