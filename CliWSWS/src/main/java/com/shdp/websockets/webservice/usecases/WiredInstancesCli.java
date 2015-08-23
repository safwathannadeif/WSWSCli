package com.shdp.websockets.webservice.usecases;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

import javax.websocket.DeploymentException;

import com.shdp.websockets.startup.InititLogs;
import com.shdp.websockets.webservice.ClientEndPtAny;
import com.shdp.websockets.webservice.ClientSendReceive;
import com.shdp.websockets.webservice.PoolThrd;

public enum WiredInstancesCli   
{
	INSTANCE;
	//
	private String theMacAddress = getTheMacAdrr() ;	
	private String strPid =  ManagementFactory.getRuntimeMXBean().getName().split("@")[0] ; 		//This process Id
	private String preFixForUniq = theMacAddress+strPid ;//This Mac Address
	private int  defCoreSizePool=2; private int defMaxSizePool=3; private int keepAliveTimePool=1 ;
	private final AtomicLong sequenceNumberReqRespId =  new AtomicLong(0);
	private PoolThrd thrdPoolCli = new PoolThrd() ;
	private  ThreadPoolExecutor threadPoolExecutorCli = thrdPoolCli.init2(defCoreSizePool,defMaxSizePool,keepAliveTimePool) ;
	
	private PoolThrd thrdPooltstCli = new PoolThrd() ;
	private ThreadPoolExecutor threadPoolExecutorTstCli = thrdPooltstCli.init2(defCoreSizePool*10,defMaxSizePool*10,keepAliveTimePool) ;
	private Map<String,ClientSendReceive>   clientSendReceiveMap =  new HashMap<String, ClientSendReceive>();
	private Logger logger =init2() ;
	private final AtomicLong sequenceNumber =  new AtomicLong(1000);
//
	public Map<String, ClientSendReceive> getClientSendReceiveMap() {
		return clientSendReceiveMap;
	}
	private String  getTheMacAdrr() 
	{
		String macAddr = null ; 

		InetAddress ip;
		try {
			ip = InetAddress.getLocalHost();
			//System.out.println("Current IP address : " + ip.getHostAddress());
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));		
			}
			macAddr = sb.toString() ;
			macAddr = macAddr.replaceAll("-", "") ;

		} catch (UnknownHostException | SocketException e) {
			e.printStackTrace();
			System.out.println("Problem getting the Mac Adddress.");
			System.out.println("SingleRef.getTheMacAdrr, calling  exit and Forcing Abort") ;
			System.exit(-1);
		}	
		return macAddr ;
	}

	public String getNextUniqueId() {

		return preFixForUniq+sequenceNumberReqRespId.getAndIncrement();  	 

	}	
	 //
	public Long getReqRespId() {
	return sequenceNumber.getAndIncrement();  	
}
	//
	public ClientSendReceive getTargetClientSendReceive(String serviceURL) throws DeploymentException, IOException 
	{
		Map<String, ClientSendReceive> mapCliSendrecev = getClientSendReceiveMap();
		serviceURL = serviceURL.trim() ;
		ClientSendReceive targetClientSendReceive = null ;
		synchronized (mapCliSendrecev) {		
			targetClientSendReceive = mapCliSendrecev.get(serviceURL);
			//Check the Client Session if life or not TODO
			if ( null == targetClientSendReceive) {

				targetClientSendReceive = new ClientSendReceive();
				targetClientSendReceive.setServiceURL(serviceURL);
				ClientEndPtAny clientEndPt = new ClientEndPtAny() ;
				clientEndPt.init2(serviceURL, serviceURL, targetClientSendReceive);
				mapCliSendrecev.put(serviceURL, targetClientSendReceive) ;

			}
		}
		
		////Debug the sessions
		
		 /*for (Map.Entry<String, ClientSendReceive> oneMapEntry  : mapCliSendrecev.entrySet()) {
	            String keyUrl = oneMapEntry.getKey();
	            ClientSendReceive clientSendReceiveValue = oneMapEntry.getValue();
            System.out.println("Debug the URL/ClientEndP Key =[" + keyUrl + "] [" + clientSendReceiveValue.getClientEndPt().getCliEndPtName()+"]");
	            Map<String, CliCallBackIF> map2 = clientSendReceiveValue.getCliCallBackMap() ;
	            for (Entry<String, CliCallBackIF> oneEntryMap2: map2.entrySet()) {

	            	System.out.println("Debug the sessions Key =[" +  oneEntryMap2.getKey() + "] [" + oneEntryMap2.getValue().getClass().getName() +"]");
	            }
	            
	            
	        }*/
		 
		return targetClientSendReceive ;

	}
//
	public void runThreadFromTheTstCliPool(Runnable toRun)
	{
		threadPoolExecutorTstCli.execute(toRun);
	}

	public void runThreadFromTheCliPool(Runnable toRun)
	{
		threadPoolExecutorCli.execute(toRun);
	}
	
	private  Logger init2()  {  
		logger = null ;	
		String workLogFileName = null ;
		try {
			workLogFileName = propLogFileName() ; 
			System.out.println("Log File=[" + workLogFileName + "]" ) ;
			logger = InititLogs.logInit2(workLogFileName,"CliUseCase") ;
		}
		catch ( IOException ex)
		{
			ex.printStackTrace();
			System.out.println("Problem Setting the logging logFileName:[" + workLogFileName +"]" ) ; 
			System.out.println("SingleRefUseCases.init2, calling  exit and Forcing Abort") ;
			System.exit(-1);
		}	
	return logger ;
	}

public Long getSequenceNumber() {
				
				return sequenceNumber.getAndIncrement();
			}


private String propLogFileName() throws IOException 
	{
	Properties	prop = new Properties();
	prop.load(WiredInstancesCli.class.getClassLoader().getResourceAsStream("cliUseCases.properties"));
	String logFileName = prop.getProperty("CliUsecaseWSWS.LogFilename").trim() ;
	System.out.println(">>>>>>>>>>prop CliUsecaseWSWS.LogFilename =[" + logFileName +"]")  ;
	prop = null ;
	return(logFileName);
	}

public Logger getLogger()
{
	
	return logger ;
}
public ThreadPoolExecutor  getThreadPoolExecutorCli()
{
	return threadPoolExecutorCli ;
}
//
public ThreadPoolExecutor  getThreadPoolExecutorTstCli()
{
	return threadPoolExecutorTstCli ;
}
//
}