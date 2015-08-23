package com.shdp.websockets.webservice;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public interface JsonConvertIF<RqRs>
{
	RqRs  convertToPOJO(String jsonStrjx,Class<RqRs> clzr) throws InstantiationException, IllegalAccessException, JsonParseException, JsonMappingException, IOException ;  
	String convertToJsonStr (RqRs rqRs) throws JsonGenerationException, JsonMappingException, IOException ;
}
