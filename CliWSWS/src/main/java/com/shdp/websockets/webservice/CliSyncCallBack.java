package com.shdp.websockets.webservice;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

//import org.codehaus.jackson.JsonParseException;
//import org.codehaus.jackson.map.JsonMappingException;

import com.shdp.websockets.webservice.ReqAndResponseAnnotaion;
import com.shdp.websockets.webservice.ServiceContext;
import com.shdp.websockets.webservice.WiredInstances;

public class CliSyncCallBack implements CliCallBackIF{
	
	@SuppressWarnings("rawtypes")
	private ServiceAgreementSyncIF servicedefinitionIFCli ;
	private CountDownLatch countDownLatch = new CountDownLatch(1) ;
	private ServiceContext  serviceContext  ;
	private String respStrJson = null ;
	
	public void resumeCallBack(String respStrJson)  {
		setRespStrJson(respStrJson);
		try
		{
		doCliCallback() ;
		}
		catch ( InstantiationException| IllegalAccessException|   IOException ex  )
		{
			throw new RuntimeException("Error doCliCallback\n", ex);
		}
		//////		getCountDownLatch().countDown();
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void doCliCallback() throws InstantiationException, IllegalAccessException, IOException	{
		
		ServiceServerAnnotation serviceDefinitioAnnotation = getServicedefinitionIFCli().getClass().getAnnotation(ServiceServerAnnotation.class);
		Class respClass = serviceDefinitioAnnotation.respClass();
		Object pojoClientResponse = respClass.newInstance() ;
		ReqAndResponseAnnotaion reqAndResponseAnnotaion = pojoClientResponse.getClass().getAnnotation(ReqAndResponseAnnotaion.class);
		JsonConvertIF  jsonRespConverterClient = WiredInstances.INSTANCE.createInstanceOfJsonConvert(reqAndResponseAnnotaion.jsonConverter()) ;
		
		
		pojoClientResponse = jsonRespConverterClient.convertToPOJO(getRespStrJson(), pojoClientResponse.getClass()) ;
			
		getServicedefinitionIFCli().setResponse(pojoClientResponse); 
		
		getCountDownLatch().countDown(); //Resume the waiting caller Thread
		
		/////ProcessClientRespIF processClientRespIF = servicedefinitionIFCli.getProcessClientResp()	;
		/////processClientRespIF.processClientResp(pojoClientResponse);  
		
	}

	public String getRespStrJson() {
		return respStrJson;
	}
	public CountDownLatch getCountDownLatch() {
		return countDownLatch;
	}

	public ServiceContext getServiceContext() {
		return serviceContext;
	}

	public void setServiceContext(ServiceContext serviceContext) {
		this.serviceContext = serviceContext;
	}

	@SuppressWarnings("rawtypes")
	public ServiceAgreementSyncIF getServicedefinitionIFCli() {
		return servicedefinitionIFCli;
	}

	@SuppressWarnings("rawtypes")
	public void setServicedefinitionIFCli(ServiceAgreementSyncIF servicedefinitionIFCli) {
		this.servicedefinitionIFCli = servicedefinitionIFCli;
}
	
	private  void setRespStrJson(String respStrJson) {
		this.respStrJson = respStrJson;
	}
	public void setCountDownLatch(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}
}
