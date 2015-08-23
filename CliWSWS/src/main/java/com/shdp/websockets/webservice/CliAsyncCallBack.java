package com.shdp.websockets.webservice;
import java.io.IOException;

//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;


import com.shdp.websockets.webservice.ReqAndResponseAnnotaion;
import com.shdp.websockets.webservice.ServiceContext;
import com.shdp.websockets.webservice.WiredInstances;
import com.shdp.websockets.webservice.usecases.WiredInstancesCli;

public class CliAsyncCallBack implements CliCallBackIF, Runnable{
	
	@SuppressWarnings("rawtypes")
	private ServiceAgreementASyncrsIF servicedefinitionIFCli ;
	
	private ServiceContext  serviceContext  ;
	private String respStrJson = null ;
	
	public void resumeCallBack(String respStrJson)  {
		setRespStrJson(respStrJson);
		WiredInstancesCli.INSTANCE.runThreadFromTheCliPool(this);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doCliCallback() throws InstantiationException, IllegalAccessException, IOException 
	{
		ServiceServerAnnotation serviceDefinitioAnnotation = getServicedefinitionIFCli().getClass().getAnnotation(ServiceServerAnnotation.class);
		Class respClass = serviceDefinitioAnnotation.respClass();
		Object pojoClientResponse = respClass.newInstance() ;
		ReqAndResponseAnnotaion reqAndResponseAnnotaion = pojoClientResponse.getClass().getAnnotation(ReqAndResponseAnnotaion.class);
		JsonConvertIF  jsonRespConverterClient = WiredInstances.INSTANCE.createInstanceOfJsonConvert(reqAndResponseAnnotaion.jsonConverter()) ;
		
		
		pojoClientResponse = jsonRespConverterClient.convertToPOJO(getRespStrJson(), pojoClientResponse.getClass()) ;
			
		getServicedefinitionIFCli().setResponse(pojoClientResponse); 		
		ProcessClientRespIF processClientRespIF = servicedefinitionIFCli.getProcessClientResp()	;
		processClientRespIF.processClientResp(pojoClientResponse);  
		
	}
	
	public void run()   {
		try
		{
		doCliCallback() ;
		}
		catch ( InstantiationException| IllegalAccessException|   IOException ex  )
		{
			throw new RuntimeException("Error doCliCallback\n", ex);
		}
	}

	public String getRespStrJson() {
		return respStrJson;
	}
	
	public ServiceContext getServiceContext() {
		return serviceContext;
	}

	public void setServiceContext(ServiceContext serviceContext) {
		this.serviceContext = serviceContext;
	}

	@SuppressWarnings("rawtypes")
	public ServiceAgreementASyncrsIF getServicedefinitionIFCli() {
		return servicedefinitionIFCli;
	}

	@SuppressWarnings("rawtypes")
	public void setServicedefinitionIFCli(ServiceAgreementASyncrsIF servicedefinitionIFCli) {
		this.servicedefinitionIFCli = servicedefinitionIFCli;
}
	
	private  void setRespStrJson(String respStrJson) {
		this.respStrJson = respStrJson;
	}
	
}
