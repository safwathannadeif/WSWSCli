package com.shdp.websockets.webservice.usecases; 
import com.shdp.websockets.webservice.ClientHandleService;
import com.shdp.websockets.webservice.ServiceAgreementASyncrsIF;
import com.shdp.websockets.webservice.ServiceAgreementOneWayIF;
import com.shdp.websockets.webservice.ServiceAgreementSyncIF;
//
//import com.mongodb.*;

// REST	http://localhost:8888/#/
public class UseCases  implements Runnable  {
	//private final   Logger logger = SingleRefUseCases.INSTANCE.getLogger() ;
	//	String servURL = "ws://localhost:8080/websockets-ws/websocketPathWS1" ; //test connect 
	//	String servURL = "ws://localhost:8080/websockets-ws/test1.html" ;       //test webService 
	//  String bigFileURL = "ws://localhost:8080/websockets-ws/websocketRtrvFileWS" ; //BigFileTesting End Pt
	// TomCat port 9080
	
	private WebServiceUseCasesEnum  makeCliWebServicesEnum = null ;
	private Integer runNum ;
	
	
	@SuppressWarnings("rawtypes")
	public void run() {
		try {
			//ClientHandleService.clientService(getServiceAgreementIFi());
			System.out.println("To Run "  + getMakeCliWebServicesEnum().getProcessMode()+" RunName=[" + getMakeCliWebServicesEnum().getWsName() +"] RunNum=" + getRunNum() );
			switch (getMakeCliWebServicesEnum().getProcessMode())
			{
			case Synchronous: 
				
				ServiceAgreementSyncIF serviceAgreementSyncIF = ServiceAgreedFactory.getSyncOneIF(getMakeCliWebServicesEnum()) ;
				ClientHandleService.clientServiceSync(serviceAgreementSyncIF);
				System.out.println("Current State Preserved and can be Continued after this response\n..... Synchronous Response after Calling:\n"+ serviceAgreementSyncIF.getResponse().toString() ) ;
				break ;
			case Asynchronous:
				ServiceAgreementASyncrsIF serviceAgreementASyncrsIF = ServiceAgreedFactory.getASyncr(getMakeCliWebServicesEnum()) ;
				ClientHandleService.clientServiceAsynrs(serviceAgreementASyncrsIF);
				
				System.out.println("ASynchronous Response should be Processed by CallBack...... after Calling:") ;
				break ;
			case OneWay:
				ServiceAgreementOneWayIF serviceAgreementOneWayIF = ServiceAgreedFactory.getOneWayIF(getMakeCliWebServicesEnum()) ; 
				ClientHandleService.clientServiceOneWay(serviceAgreementOneWayIF);
				System.out.println("OneWay No  Response. Look for the Server logs to the Requset Processed...... ") ;
				System.out.println(" OneWay Dont Try to get  this response\n..... ....") ;
				break ;
			}
			System.out.println("End Run Synchronous RunName=[" + getMakeCliWebServicesEnum().getWsName() +"] RunNum=" + getRunNum() );
		  } catch (Exception e) {
		    e.printStackTrace();
		  }
		
		
		}
	public static void main(String[] args) throws Exception {
		
        
		Integer count = 0 ;
		System.out.println("Working Concurrency with :=" + ServiceAgreedFactory.getNoOfEnumWSs() );
		for (WebServiceUseCasesEnum wsToRun : WebServiceUseCasesEnum.values())
		{
			if ( wsToRun.getDoIt() )
			{		
				UseCases tRunnable = new UseCases() ;
				tRunnable.setRunNum(++count);
				tRunnable.setMakeCliWebServicesEnum(wsToRun);
				System.out.println("Start Run WebSevice:" + wsToRun.getWsName()) ;
				WiredInstancesCli.INSTANCE.runThreadFromTheTstCliPool(tRunnable);
				System.out.println("End Run WebSevice:" + wsToRun.getWsName()) ;
				
			}
			
		}
		//Schedule the shutdown for the pools since yr are done
		//
//**** 					Be Careful with Pools shutdown: 
//****  If Asynchronous run is awaiting a response and the pool [ThreadPoolExecutorCli] shutdown,  the incoming response 
//****	will not be able to grab a thread from the pool, consequently  the  Asynchronous response will not be processed. 
//****	At this situation the Java Thread pool will raise an error.
		
		System.out.println("Going to Wait for ThrdPools Shutdown : ThreadPoolExecutorCli and ThreadPoolExecutorTstCli ...") ;
		Thread.sleep(60*1000);
		WiredInstancesCli.INSTANCE.getThreadPoolExecutorCli().shutdown();
		WiredInstancesCli.INSTANCE.getThreadPoolExecutorTstCli().shutdown();
		
		System.out.println("Done.") ;
		
	}
	
	public WebServiceUseCasesEnum getMakeCliWebServicesEnum() {
		return makeCliWebServicesEnum;
	}
	public void setMakeCliWebServicesEnum(
			WebServiceUseCasesEnum makeCliWebServicesEnum) {
		this.makeCliWebServicesEnum = makeCliWebServicesEnum;
	}
	public Integer getRunNum() {
		return runNum;
	}
	public void setRunNum(Integer runNum) {
		this.runNum = runNum;
	}

}

