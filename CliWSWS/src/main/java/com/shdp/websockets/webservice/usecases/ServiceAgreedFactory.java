package com.shdp.websockets.webservice.usecases;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import com.shdp.websockets.webservice.ServiceAgreementASyncrsIF;
import com.shdp.websockets.webservice.ServiceAgreementOneWayIF;
import com.shdp.websockets.webservice.ServiceAgreementSyncIF;
import com.shdp.websockets.webservice.usecases.employeews.ProcessClientRespEmp;
import com.shdp.websockets.webservice.usecases.employeews.ReqEmp;
import com.shdp.websockets.webservice.usecases.employeews.ServiceAgreedEmpASyncr;
import com.shdp.websockets.webservice.usecases.employeews.ServiceAgreedEmpSync;
import com.shdp.websockets.webservice.usecases.rtrvfilews.ReqRtrvFileWS;
import com.shdp.websockets.webservice.usecases.rtrvfilews.ServiceAgreedRtrvFileSyncWS;
import com.shdp.websockets.webservice.usecases.ws1.ProcessClientRespWS1;
import com.shdp.websockets.webservice.usecases.ws1.ReqWS1;
import com.shdp.websockets.webservice.usecases.ws1.ServiceAgreedWS1Asyncr;
import com.shdp.websockets.webservice.usecases.ws1.ServiceAgreedWS1Sync;
import com.shdp.websockets.webservice.usecases.ws1.ServiceAgreeddWS1OneWay;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ServiceAgreedFactory {
	
	static String servURL ="ws://localhost:8080/websockets-ws/websocketPathWS1" ;
	public static int getNoOfEnumWSs()
	{
		return (WebServiceUseCasesEnum.values().length) ;
	}
	
	public static ServiceAgreementASyncrsIF getASyncr(WebServiceUseCasesEnum makeCliWebServicesEnum)
	{
		switch(makeCliWebServicesEnum.getWsName())
		{
		case "makeOneASyncrEmpWS":
			 return  makeOneASyncrEmpWS()  ;
		case "makeOneASyncnrwWS1":
			return makeOneASyncnrwWS1() ;
			
		default:	 
			return null ;
		}
	}
	//
	public static ServiceAgreementSyncIF getSyncOneIF(WebServiceUseCasesEnum makeCliWebServicesEnum)
	{
		switch(makeCliWebServicesEnum.getWsName())
		{
		case "makeOneSyncWS1":
			 return  makeOneSyncWS1()  ;
		case "makeOneSyncEmpWS":
			return makeOneSyncEmpWS() ;
		case "makeOneSyncRtrvFileWS":
			return makeOneSyncRtrvFileWS();
		default:	 
			return null ;
		}
	}
	//
	public static ServiceAgreementOneWayIF getOneWayIF(WebServiceUseCasesEnum makeCliWebServicesEnum)
	{
		
		switch(makeCliWebServicesEnum.getWsName())
		{
		case "makeOneRunOneWayWS1":
			 return  makeOneRunOneWayWS1()  ;
		default:	 
			return null ;
		}
	}
	//
	public static ServiceAgreementOneWayIF makeOneRunOneWayWS1() 
	{
	ReqWS1 cliReq11 = new ReqWS1() ;
	cliReq11.setThreadName(Thread.currentThread().getName());
	Long longId =WiredInstancesCli.INSTANCE.getReqRespId() ;
	cliReq11.setReqInt1((longId.intValue()));
	cliReq11.setReqStr1("StrRequest11-xxxxYzz"+longId);
	cliReq11.setCurStr("CurSt"+longId);
	cliReq11.setReqRespIdLong(longId);
	ServiceAgreementOneWayIF serviceDefinitionAnn11Cli = new ServiceAgreeddWS1OneWay() ;
	serviceDefinitionAnn11Cli.setServiceURL(servURL);
	serviceDefinitionAnn11Cli.setRequest(cliReq11);
	
	return (serviceDefinitionAnn11Cli);
	}
	//	
	public static ServiceAgreementASyncrsIF makeOneASyncnrwWS1() 
	{
	ReqWS1 cliReq11 = new ReqWS1() ;
	Long longId =WiredInstancesCli.INSTANCE.getReqRespId() ;
	cliReq11.setReqInt1((longId.intValue()));
	cliReq11.setReqStr1("StrRequest11-xxxxYzz"+longId);
	cliReq11.setCurStr("CurSt"+longId);
	cliReq11.setReqRespIdLong(longId);
	//ServiceAgreementIF serviceDefinitionAnn11Cli  = new ServiceAgreedWS1Asyncr() ;
	ServiceAgreedWS1Asyncr serviceDefinitionAnn11Cli  = new ServiceAgreedWS1Asyncr() ;
	serviceDefinitionAnn11Cli.setServiceURL(servURL);
	ProcessClientRespWS1 procCliResp =new ProcessClientRespWS1() ;
	serviceDefinitionAnn11Cli.setProcessClientResp(procCliResp);
	serviceDefinitionAnn11Cli.setRequest(cliReq11);
    return (serviceDefinitionAnn11Cli);
	}
	//
	public static ServiceAgreementSyncIF makeOneSyncWS1()
	{
	ReqWS1 cliReq11 = new ReqWS1() ;
	Long longId =WiredInstancesCli.INSTANCE.getReqRespId() ;
	cliReq11.setReqInt1((longId.intValue()));
	cliReq11.setReqStr1("StrRequest11-xxxxYzz"+longId);
	cliReq11.setCurStr("CurSt"+longId);
	cliReq11.setReqRespIdLong(longId);
	ServiceAgreedWS1Sync serviceDefinitionAnn11Cli  = new ServiceAgreedWS1Sync() ;
	serviceDefinitionAnn11Cli.setServiceURL(servURL);
	/*ProcessClientRespWS1 procCliResp =new ProcessClientRespWS1() ;
	serviceDefinitionAnn11Cli.setProcessClientResp(procCliResp);*/
	serviceDefinitionAnn11Cli.setRequest(cliReq11);
    return (serviceDefinitionAnn11Cli);
	}
	//
	public static ServiceAgreementSyncIF makeOneSyncEmpWS() 
	{
	//Long longId =SingleRef.INSTANCE.getReqRespId() ;
	ReqEmp reqEmp = new ReqEmp() ;
	reqEmp.setReqBy("Manager");
	reqEmp.setReqWhen(new Date()) ;
	
	ServiceAgreedEmpSync serviceDefinitionAnn11Cli  = new ServiceAgreedEmpSync() ;
	serviceDefinitionAnn11Cli.setServiceURL(servURL);
	serviceDefinitionAnn11Cli.setRequest(reqEmp);
    return (serviceDefinitionAnn11Cli);
	}	
	//
	public static ServiceAgreementASyncrsIF makeOneASyncrEmpWS() 
	{
	//Long longId =SingleRef.INSTANCE.getReqRespId() ;
	ReqEmp reqEmp = new ReqEmp() ;
	reqEmp.setReqBy("Manager");
	reqEmp.setReqWhen(new Date()) ;
	
	ServiceAgreedEmpASyncr serviceDefinitionAnn11Cli  = new ServiceAgreedEmpASyncr() ;
	serviceDefinitionAnn11Cli.setServiceURL(servURL);
	ProcessClientRespEmp procCliResp =new ProcessClientRespEmp() ;
	serviceDefinitionAnn11Cli.setProcessClientResp(procCliResp);
	serviceDefinitionAnn11Cli.setRequest(reqEmp);
    return (serviceDefinitionAnn11Cli);
	}	
	//
	public static ServiceAgreementSyncIF makeOneSyncRtrvFileWS() 
	{
	Properties	prop = getFileRtrvProps() ;
	String pathRtrvFileWS = prop.getProperty("pathRtrvFileWS").trim();  
	String filenameRtrvFileWS= prop.getProperty("filenameRtrvFileWS").trim() ;
	ReqRtrvFileWS reqFileWS = new ReqRtrvFileWS() ;
	reqFileWS.setPath(pathRtrvFileWS);
	reqFileWS.setFilename(filenameRtrvFileWS);
	//
	
	ServiceAgreedRtrvFileSyncWS serviceAgreedRtrvFileSyncWS = new ServiceAgreedRtrvFileSyncWS() ; //serviceAgreementIFi
	serviceAgreedRtrvFileSyncWS.setRequest(reqFileWS);
	serviceAgreedRtrvFileSyncWS.setServiceURL("ws://localhost:8080/websockets-ws/websocketRtrvFileWS");
	return (serviceAgreedRtrvFileSyncWS);
	}
	public static Properties getFileRtrvProps() 
	{
		Properties	prop = new Properties();
		try {
			prop.load(WiredInstancesCli.class.getClassLoader().getResourceAsStream("cliUseCases.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return(prop);
	}

}
