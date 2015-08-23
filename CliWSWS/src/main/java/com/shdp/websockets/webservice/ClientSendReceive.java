package com.shdp.websockets.webservice;

/*import java.nio.file.Files;
import java.nio.file.Paths;*/
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.shdp.websockets.webservice.usecases.WiredInstancesCli;

//We need the Service Definition  
public class ClientSendReceive 
{
	
	private String serviceURL  ;
	private Map<String, CliCallBackIF> cliCallBackMap = new HashMap<String, CliCallBackIF>(); //The CallBack Subject specific ClientSendReceiver
	private ClientEndPtAny clientEndPt ;
	
	//private final static  Logger logger = Logger.getLogger(ClientSendReceive.class.getName()) ;
	private final   Logger logger = WiredInstancesCli.INSTANCE.getLogger() ;
	
	//private final static Logger logger = Logger.getLogger(ProcessClientRespWS1.class.getName());
	public void  sendAllTxt(String txtToSend) {
			////////////////////////////////
		logger.info("Client Send\n" + txtToSend );
			///////////////////////////////

		//getClientEndPt().getCliSession().getBasicRemote().sendText(txtToSend);
		getClientEndPt().getCliSession().getAsyncRemote().sendText(txtToSend);
		
		
	}
	
	public void  onReceiveTxt(String rcvAllTxt ) throws JsonParseException, JsonMappingException, IOException  {
		////////////////////////////////
		logger.info("Client Received\n" + rcvAllTxt );
		///////////////////////////////

		int headerLength  = WiredInstances.INSTANCE.getFixedHeaderLength() ;
		int lenOfContextObj = Integer.valueOf( rcvAllTxt.substring(0,headerLength).trim()) ;
		
		String jsonContextStr = rcvAllTxt.substring(headerLength,lenOfContextObj+headerLength);
		
		String jsonResptextStr = rcvAllTxt.substring(lenOfContextObj+headerLength) ;
		
		ObjectMapper objectMapper = new ObjectMapper();
		ServiceContext serviceContext = objectMapper.readValue(jsonContextStr,ServiceContext.class) ;
	
		serviceContext.setWorkerThreadName("ClientThreadName -- " + Thread.currentThread().getName());
		serviceContext.setWorkerSessionId("ClientSessionId -- " + getClientEndPt().getCliSession().getId() );
	    
		CliCallBackIF cliCallBackIF = getCliCallBackMap().get(serviceContext.getReqRespLinkStrId());
		//Check if null //TODO
		//Remove the CliCallBackIF
		getCliCallBackMap().remove(serviceContext.getReqRespLinkStrId());
		
		//Release the calling Thread to execute  the callback
		cliCallBackIF.resumeCallBack(jsonResptextStr);
	}
	
	public String getServiceURL() {
		return serviceURL;
	}
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}

	public ClientEndPtAny getClientEndPt() {
		return clientEndPt;
	}

	
	public void setClientEndPt(ClientEndPtAny clientEndPti) {
		this.clientEndPt = clientEndPti;
	}

	public Map<String, CliCallBackIF> getCliCallBackMap() { 
		return cliCallBackMap;
	}
	//
	
}
