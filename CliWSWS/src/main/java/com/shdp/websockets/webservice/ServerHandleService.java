package com.shdp.websockets.webservice;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;


@SuppressWarnings({ "unchecked", "rawtypes" })
public class ServerHandleService {
	
	private ServerReceiveSend  serverReceiveSend ;
	
	
	public ServerHandleService(ServerReceiveSend  serverReceiveSendi)
	{
		serverReceiveSend=serverReceiveSendi ;
	}
	
	public void serverService(ServiceAgreementBaseIF servicedefinitionIFServer, String jsonReqtextStr) throws InstantiationException, IllegalAccessException, JsonParseException, JsonMappingException, IOException 
	{
		//get the Request and convert it 
		ServiceServerAnnotation serviceDefinitioAnnotation = servicedefinitionIFServer.getClass().getAnnotation(ServiceServerAnnotation.class) ;
		Class reqClass =serviceDefinitioAnnotation.reqClass() ;
		Object objReqFromCli = reqClass.newInstance() ;
		ReqAndResponseAnnotaion reqAnnotaion = objReqFromCli.getClass().getAnnotation(ReqAndResponseAnnotaion.class);
		JsonConvertIF  jsonReqConverter = WiredInstances.INSTANCE.createInstanceOfJsonConvert(reqAnnotaion.jsonConverter()) ;
		objReqFromCli = jsonReqConverter.convertToPOJO(jsonReqtextStr,objReqFromCli.getClass());
		ProcessModeEnum processModeEnum =serviceDefinitioAnnotation.processModeEnum() ;
		
		
		switch (processModeEnum.getMode())
		{
				case "Synchronous": case "Asynchronous":
					makeServerSyncOrAsynchronous(serviceDefinitioAnnotation,objReqFromCli) ;
					break ;
				case "OneWay":
					makeServerOneWay(serviceDefinitioAnnotation,objReqFromCli) ;
					break ;
				default:
					throw new RuntimeException("error undefine process mode ");
		}			
	}
//
	private void makeServerSyncOrAsynchronous(ServiceServerAnnotation serviceDefinitioAnnotation,Object serverRequestObj) throws InstantiationException, IllegalAccessException, JsonGenerationException, JsonMappingException, IOException 
	{
		Class respClass = serviceDefinitioAnnotation.respClass() ;
		Object serverRespObj  = respClass.newInstance() ;
		ProcessServerReqIF  processServerReqIF = WiredInstances.INSTANCE.createInstanceOfProcessServerReq(serviceDefinitioAnnotation.processServerReqClass()) ;
		processServerReqIF.processServerReq(serverRequestObj, serverRespObj);
		
		ReqAndResponseAnnotaion responseAnnotaion = serverRespObj.getClass().getAnnotation(ReqAndResponseAnnotaion.class);
		JsonConvertIF  jsonRespConverterResp = WiredInstances.INSTANCE.createInstanceOfJsonConvert(responseAnnotaion.jsonConverter()) ;
		String jsonResptr= jsonRespConverterResp.convertToJsonStr(serverRespObj);
		serverReceiveSend.sendAllResptxt(jsonResptr);
	}
	private void makeServerOneWay(ServiceServerAnnotation serviceDefinitioAnnotation,Object serverRequestObj) throws InstantiationException, IllegalAccessException 
	{
		
		ProcessServerReqIF  processServerReqIF = WiredInstances.INSTANCE.createInstanceOfProcessServerReq(serviceDefinitioAnnotation.processServerReqClass()) ;
		processServerReqIF.processServerReq(serverRequestObj, null);
		
		
	}
}
