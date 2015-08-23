package com.shdp.websockets.webservice;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Logger;

import javax.websocket.Session;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

public class ServerReceiveSend {
	protected Session session ;
	protected ServiceContext serviceContext;
	
	//private final   Logger logger = SingleRef.INSTANCE.getLogger().ServerReceiveSend.class.getName()) ; 
	//@SuppressWarnings("static-access")
	private final   Logger logger = WiredInstances.INSTANCE.getLogger() ;
	//.getLogger(ServerReceiveSend.class.getName()); 
	

	
	
	public ServerReceiveSend(){
		
	}
	public ServerReceiveSend(Session sessionx){
		setSession(sessionx);
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void onReceive(String recvAllTxt) throws JsonParseException, JsonMappingException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, NumberFormatException 
	{
	
	////////////////////////////////
		logger.info("Server Received\n" + recvAllTxt );
	///////////////////////////////
		int headerLength  = WiredInstances.INSTANCE.getFixedHeaderLength() ;
		
		int lenOfContextObj = Integer.valueOf( recvAllTxt.substring(0,headerLength).trim()) ;
		
		String jsonContextStr = recvAllTxt.substring(headerLength,lenOfContextObj+headerLength);
		
		String jsonReqtextStr = recvAllTxt.substring(lenOfContextObj+headerLength) ;
		
		ObjectMapper objectMapper = new ObjectMapper();
		serviceContext = objectMapper.readValue(jsonContextStr,ServiceContext.class) ;
		
		/////
		String serviceDefinitionClassName  =serviceContext.getServiceAgreementClassName() ;
		Class cz = Class.forName(serviceDefinitionClassName);
		
		ServiceAgreementBaseIF  serviceDefinitionAnnotedIF  =
				WiredInstances.INSTANCE.createInstanceOfBaseServiceAgreement(cz) ;
		//New
		////New ServiceServerAnnotation serviceServerAnnotation = cz.getClass().getAnnotation(ServiceServerAnnotation.class);
		//New
		//ServiceDefinitioAnnotation serviceDefinitioAnnotation = serviceDefinitionAnnotedIF.getClass().getAnnotation(ServiceDefinitioAnnotation.class);
		ServerHandleService serverHandleService = new ServerHandleService(this) ;
		
		serverHandleService.serverService(serviceDefinitionAnnotedIF, jsonReqtextStr);
	}
		
		public void sendAllResptxt(String serverResptxtJson) throws JsonGenerationException, JsonMappingException, IOException 
		{
			String fixedLenFormatHeader = WiredInstances.INSTANCE.getFormatThFixedHeaderLength() ;
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
		    StringWriter sw = new StringWriter();
		    getServiceContext().setWorkerThreadName("ServerThreadName -- " + Thread.currentThread().getName());
		    getServiceContext().setWorkerSessionId("ServerSessionId -- " + getSession().getId() );
			objectMapper.writeValue(sw,getServiceContext()) ; 
			String jsonContextStr =sw.toString() ;
			String padded = String.format(fixedLenFormatHeader, jsonContextStr.length()) ; //Total Length 11 +"\n" = Total 12
			StringBuilder sbAllrespToSend= new StringBuilder().append(padded).append("\n").append(jsonContextStr).append(serverResptxtJson);
			String toSendStr = sbAllrespToSend.toString() ;
			////////////////////////////////
			logger.info("Server Send\n" +  toSendStr);
			///////////////////////////////
			send(toSendStr) ;
		}
		
		

	//
	protected void send(String outMsg) throws IOException {
	
		getSession().getBasicRemote().sendText(outMsg);
	}
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
	public ServiceContext getServiceContext() {
		return serviceContext;
	}
	public void setServiceContext(ServiceContext serviceContext) {
		this.serviceContext = serviceContext;
	}

}
