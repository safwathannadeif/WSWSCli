package com.shdp.websockets.webservice;

import java.io.IOException;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

public interface CliCallBackIF {
	public void resumeCallBack(String respJosonStr)  ; 
	public void doCliCallback() throws JsonParseException, JsonMappingException, InstantiationException, IllegalAccessException, IOException ; 

}
