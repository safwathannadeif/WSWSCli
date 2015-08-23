package com.shdp.websockets.webservice.usecases;
import java.io.IOException;
import java.util.Properties;

import javax.websocket.DeploymentException;

import com.shdp.websockets.webservice.ClientHandleService;
import com.shdp.websockets.webservice.usecases.rtrvfilews.ReqRtrvFileWS;
import com.shdp.websockets.webservice.usecases.rtrvfilews.ServiceAgreedRtrvFileSyncWS;

public class RtrvFileUseCaseTstBlock_SA   {
	//   private final   Logger logger = SingleRefUseCases.INSTANCE.getLogger() ;
	//	String servURL = "ws://localhost:8080/websockets-ws/websocketPathWS1" ; //test connect 
	//	String servURL = "ws://localhost:8080/websockets-ws/test1.html" ;       //test webService 
	//  String bigFileURL = "ws://localhost:8080/websockets-ws/websocketRtrvFileWS" ; //BigFileTesting End Pt
		
	public static void main(String[] args) throws InterruptedException, InstantiationException, IllegalAccessException, DeploymentException, IOException {
		
		//System.out.println("Working Concurrency with :=" + ServiceAgreedFactory.getNoOfEnumWSs() );
		ServiceAgreedRtrvFileSyncWS serviceAgreedRtrvFileSyncWS = new ServiceAgreedRtrvFileSyncWS() ; //serviceAgreementIFi
		/////
		Properties	prop = new Properties();
		prop.load(WiredInstancesCli.class.getClassLoader().getResourceAsStream("cliUseCases.properties"));
		
		String pathRtrvFileWS = prop.getProperty("pathRtrvFileWS").trim();  
		String filenameRtrvFileWS= prop.getProperty("filenameRtrvFileWS").trim() ;
		ReqRtrvFileWS reqFileWS = new ReqRtrvFileWS() ;
		reqFileWS.setPath(pathRtrvFileWS);
		reqFileWS.setFilename(filenameRtrvFileWS);
		/////
		serviceAgreedRtrvFileSyncWS.setRequest(reqFileWS);
		serviceAgreedRtrvFileSyncWS.setServiceURL("ws://localhost:8080/websockets-ws/websocketRtrvFileWS");
		//serviceAgreedRtrvFileSyncWS.setServiceURL("ws://localhost:9080/websockets-ws/websocketRtrvFileWS"); //TomCat
		ClientHandleService.clientServiceSync(serviceAgreedRtrvFileSyncWS);
		//clientService(serviceAgreedRtrvFileSyncWS);
		//After Blocking
		/////			System.out.println("Response..\n" + serviceAgreedRtrvFileSyncWS.getResponse().toString()) ;
		int i = 0 ;
		for(String str: serviceAgreedRtrvFileSyncWS.getResponse().getList())
		{
			System.out.println("ResponStr=["+  ++i + "] [" + str + "]" ) ;
		}
		System.out.println("Total Num of StrLines in file=" + serviceAgreedRtrvFileSyncWS.getResponse().getArrySize()) ;
	}
}

