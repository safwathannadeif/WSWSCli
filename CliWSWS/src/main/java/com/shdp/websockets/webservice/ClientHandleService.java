package com.shdp.websockets.webservice;
import java.io.IOException;
import java.io.StringWriter;

import javax.websocket.DeploymentException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;

import com.shdp.websockets.webservice.ServiceContext;
import com.shdp.websockets.webservice.WiredInstances;
import com.shdp.websockets.webservice.usecases.WiredInstancesCli;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class ClientHandleService  {
	
public static void clientServiceAsynrs(ServiceAgreementASyncrsIF servicedefinitionIFCli) throws DeploymentException, IOException, InterruptedException, InstantiationException, IllegalAccessException
{
	getAndCheckServiceServerAnnotation(servicedefinitionIFCli, ProcessModeEnum.Asynchronous);
	ServiceContext serviceContext = context (servicedefinitionIFCli) ;
	makeCliCallBackAsync(servicedefinitionIFCli,serviceContext);
}
//
public static void clientServiceSync(ServiceAgreementSyncIF servicedefinitionIFCli) throws DeploymentException, IOException, InterruptedException, InstantiationException, IllegalAccessException
{
	getAndCheckServiceServerAnnotation(servicedefinitionIFCli, ProcessModeEnum.Synchronous);
	ServiceContext serviceContext = context (servicedefinitionIFCli) ;
	makeCliCallBackSync(servicedefinitionIFCli,serviceContext) ;
	
}
//
public static void clientServiceOneWay(ServiceAgreementOneWayIF servicedefinitionIFCli) throws DeploymentException, IOException, InterruptedException, InstantiationException, IllegalAccessException
{
	
	getAndCheckServiceServerAnnotation(servicedefinitionIFCli, ProcessModeEnum.OneWay);
	ServiceContext serviceContext = context (servicedefinitionIFCli) ;
	makeCliOnewWay(servicedefinitionIFCli,serviceContext);
}
//

private static ServiceContext context(ServiceAgreementBaseIF serviceAgreementBaseIFi) 
{
	ServiceContext serviceContext = new ServiceContext();
	serviceContext.setReqRespLinkStrId(WiredInstancesCli.INSTANCE.getNextUniqueId());
	serviceContext.setServiceURL(serviceAgreementBaseIFi.getServiceURL().trim());
	serviceContext.setServiceAgreementClassName(serviceAgreementBaseIFi.getClass().getName());
	serviceContext.setWorkerThreadName("ClientThreadName -- " + Thread.currentThread().getName()); //Debug session purpose
	return(serviceContext) ; 
}
private static void getAndCheckServiceServerAnnotation(ServiceAgreementBaseIF serviceAgreementBaseIFii, ProcessModeEnum processModeEnum)
{
ServiceServerAnnotation serviceDefinitioAnnotation = serviceAgreementBaseIFii.getClass().getAnnotation(ServiceServerAnnotation.class) ;
if (null == serviceDefinitioAnnotation)
{
	String ClassName = serviceAgreementBaseIFii.getClass().getName();
	throw new RuntimeException ("Error ClassName:[" + ClassName +"], Should be Annotated. Use ServicServeAnnotation to annotate the Service Agreement  ["+ ClassName +"]\n Please have a look to the Readme note to see where are the examples templates ") ;
}
if ( serviceDefinitioAnnotation.processModeEnum() != processModeEnum )
{
	throw new RuntimeException("Error:processModeEnum Should be[" + processModeEnum.getMode() +"]  Not [" + serviceDefinitioAnnotation.processModeEnum().getMode() + "]" ) ;			 
}

//return(serviceDefinitioAnnotation) ;
}
///////////////////////////////////////////////////////
/*public static void clientService(ServiceAgreementIF servicedefinitionIFCli) throws DeploymentException, IOException, InterruptedException, InstantiationException, IllegalAccessException
{
	ServiceContext serviceContext = new ServiceContext();
	serviceContext.setReqRespLinkStrId(SingleRefUseCases.INSTANCE.getNextUniqueId());
	serviceContext.setServiceURL(servicedefinitionIFCli.getServiceURL().trim());
	serviceContext.setServiceAgreementClassName(servicedefinitionIFCli.getClass().getName());
	serviceContext.setWorkerThreadName("ClientThreadName -- " + Thread.currentThread().getName()); //Debug session purpose
	ServiceServerAnnotation serviceDefinitioAnnotation = servicedefinitionIFCli.getClass().getAnnotation(ServiceServerAnnotation.class) ;
	ProcessModeEnum processModeEnum =serviceDefinitioAnnotation.processModeEnum() ;
	switch (processModeEnum)
	{
	case Synchronous: 
		makeCliCallBackSync(servicedefinitionIFCli,serviceContext) ;
		break ;
	case Asynchronous:
		makeCliCallBackAsync(servicedefinitionIFCli,serviceContext) ;
		break ;
	case OneWay:
		makeCliOnewWay(servicedefinitionIFCli,serviceContext) ;
		break ;
	}
	 
	
}	*/
	private static void makeCliCallBackSync(ServiceAgreementSyncIF servicedefinitionIFClii,ServiceContext serviceContexti) throws DeploymentException, IOException, InterruptedException, InstantiationException, IllegalAccessException 
	{
		//Make the Sync client callback
		CliSyncCallBack cliSyncCallBack = new CliSyncCallBack() ;
		cliSyncCallBack.setServiceContext(serviceContexti);
		serviceContexti.setMode(ProcessModeEnum.Synchronous);
		cliSyncCallBack.setServicedefinitionIFCli(servicedefinitionIFClii);
		ClientSendReceive clientSendReceive = WiredInstancesCli.INSTANCE.getTargetClientSendReceive(servicedefinitionIFClii.getServiceURL());
		serviceContexti.setWorkerSessionId( "ClientSessionId -- " + clientSendReceive.getClientEndPt().getCliSession().getId() ) ;//Debug session purpose
		clientSendReceive.getCliCallBackMap().put(cliSyncCallBack.getServiceContext().getReqRespLinkStrId(), cliSyncCallBack);
		
		String sendTxti = makeAllJsonStr(servicedefinitionIFClii  , serviceContexti) ;
		
		clientSendReceive.sendAllTxt(sendTxti);
		
		cliSyncCallBack.getCountDownLatch().await(); //Client Calling thread is waiting now 
		
		
		
	}
	private static void makeCliCallBackAsync(ServiceAgreementASyncrsIF servicedefinitionIFClii,ServiceContext serviceContexti) throws DeploymentException, IOException, InstantiationException, IllegalAccessException 
	{
		//Make the Sync client callback
		CliAsyncCallBack cliAsyncCallBack = new CliAsyncCallBack() ;
		cliAsyncCallBack.setServiceContext(serviceContexti);
		serviceContexti.setMode(ProcessModeEnum.Asynchronous);
		cliAsyncCallBack.setServicedefinitionIFCli(servicedefinitionIFClii);
		ClientSendReceive clientSendReceive = WiredInstancesCli.INSTANCE.getTargetClientSendReceive(servicedefinitionIFClii.getServiceURL());
		serviceContexti.setWorkerSessionId( "ClientSessionId -- " + clientSendReceive.getClientEndPt().getCliSession().getId() ) ;//Debug session purpose
		clientSendReceive.getCliCallBackMap().put(cliAsyncCallBack.getServiceContext().getReqRespLinkStrId(), cliAsyncCallBack);
		String sendTxti = makeAllJsonStr(servicedefinitionIFClii  , serviceContexti) ;
		
		clientSendReceive.sendAllTxt(sendTxti);
		
	}
	private static void makeCliOnewWay(ServiceAgreementOneWayIF servicedefinitionIFClii,ServiceContext serviceContexti) throws DeploymentException, IOException, InstantiationException, IllegalAccessException 
	{
		ClientSendReceive clientSendReceive = WiredInstancesCli.INSTANCE.getTargetClientSendReceive(servicedefinitionIFClii.getServiceURL());
		serviceContexti.setWorkerSessionId( "ClientSessionId -- " + clientSendReceive.getClientEndPt().getCliSession().getId() ) ;//Debug session purpose
		serviceContexti.setMode(ProcessModeEnum.OneWay); 
		String sendTxti = makeAllJsonStr(servicedefinitionIFClii,serviceContexti) ;
		clientSendReceive.sendAllTxt(sendTxti);
		
	}
//
	private static String makeAllJsonStr(ServiceAgreementBaseIF servicedefinitionIFCli,ServiceContext serviceContext) throws JsonGenerationException, JsonMappingException, IOException, InstantiationException, IllegalAccessException 
	{
		//Create the ServiceContext and ConvertoJosn with the Length
		ObjectMapper objectMapper = new ObjectMapper();
	    objectMapper.enable(SerializationConfig.Feature.INDENT_OUTPUT);
	    StringWriter sw = new StringWriter();
		objectMapper.writeValue(sw,serviceContext) ; 
		String serviceContextJsonStr =sw.toString() ;
		
		String formatThfixedLength =WiredInstances.INSTANCE.getFormatThFixedHeaderLength() ;
		String padded = String.format(formatThfixedLength, serviceContextJsonStr.length()) ; //Total Length 11 +"\n" = Total 12

		Object pojoClientRequest = servicedefinitionIFCli.getRequest() ;
		ReqAndResponseAnnotaion cliReqAnnotaion = pojoClientRequest.getClass().getAnnotation(ReqAndResponseAnnotaion.class);
		JsonConvertIF  jsonRespConverterClient = WiredInstances.INSTANCE.createInstanceOfJsonConvert(cliReqAnnotaion.jsonConverter()) ;
		
		String reqCliJsonStr  = jsonRespConverterClient.convertToJsonStr(servicedefinitionIFCli.getRequest());
		//
		StringBuilder sbAllToSend= new StringBuilder().append(padded).append("\n").append(serviceContextJsonStr).append(reqCliJsonStr);
		
		return ( sbAllToSend.toString() ) ;
		
		
	}
}
	