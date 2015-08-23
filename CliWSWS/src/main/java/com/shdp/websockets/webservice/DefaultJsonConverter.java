package com.shdp.websockets.webservice;

import java.io.IOException;
import java.io.StringWriter;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public final class DefaultJsonConverter implements JsonConvertIF<Object>   {
		
	@SuppressWarnings("unchecked")
	public Object convertToPOJO(String jsonstrjx,@SuppressWarnings("rawtypes") Class clazzz) throws InstantiationException, IllegalAccessException, JsonParseException, JsonMappingException, IOException  {
		ObjectMapper objectMapper = new ObjectMapper();
		Object objx =clazzz.newInstance() ;
		objx = objectMapper.readValue(jsonstrjx,clazzz) ;
		 return objx ;
	
	}

	public String convertToJsonStr(Object tx) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
	    StringWriter sw = new StringWriter();
		objectMapper.writeValue(sw,tx) ; 
		String jsonStr =sw.toString() ;
	    return jsonStr ;
	}
}





