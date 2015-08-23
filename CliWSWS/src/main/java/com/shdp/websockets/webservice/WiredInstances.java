package com.shdp.websockets.webservice;

import java.io.IOException;
import java.util.Properties;

import java.util.logging.Logger;

import com.shdp.websockets.startup.InititLogs;


public enum WiredInstances
{
	INSTANCE;
	private final int fixedHeaderLength = 12 ;
	private final String formatThFixedHeaderLength = "%11s" ;  //the Total Length is 12 since at the end added with "\n"

	private Logger logger =init2() ;

	
	public  ServiceAgreementBaseIF<?,?> createInstanceOfBaseServiceAgreement(Class<? extends ServiceAgreementBaseIF<?,?>> clazz) throws InstantiationException, IllegalAccessException  {
		return clazz.newInstance();
	}

	public  JsonConvertIF<?> createInstanceOfJsonConvert(Class<? extends JsonConvertIF<?>> clazz) throws InstantiationException, IllegalAccessException  {
		return clazz.newInstance();
	}

	public  ProcessServerReqIF<?,?>  createInstanceOfProcessServerReq (Class<? extends ProcessServerReqIF<?,?> > pclazz) throws InstantiationException, IllegalAccessException  {
		return pclazz.newInstance();
	}
	public static ProcessClientRespIF<?>  createInstanceOfProcessClientResp (Class<? extends ProcessClientRespIF<?> > pclazzzz) throws InstantiationException, IllegalAccessException   {
		return pclazzzz.newInstance();
	}

	public int getFixedHeaderLength()
	{
		return(fixedHeaderLength) ;
	}

	public String getFormatThFixedHeaderLength() {
		return formatThFixedHeaderLength;
	}
	
	private String propLogFileName() throws IOException 
	{
		Properties	prop = new Properties();
		prop.load(WiredInstances.class.getClassLoader().getResourceAsStream("wsws.properties"));
		String logFileName = prop.getProperty("ServerWSWS.LogFilename").trim() ;
		System.out.println(">>>>>>>>>>prop ServerWSWS.LogFilename =[" + logFileName +"]")  ;
		prop = null ;
		return(logFileName);
	}
	private  Logger init2()  {  
		logger = null ;	
		String workLogFileName = null ;
		String nameid="Server" ;
		try {
			workLogFileName = propLogFileName() ; 
			System.out.println("Log File=[" + workLogFileName + "]" ) ;
			logger = InititLogs.logInit2(workLogFileName,nameid) ;
		}
		catch ( IOException ex)
		{
			ex.printStackTrace();
			System.out.println("Problem Setting the logging logFileName:[" + workLogFileName +"]" ) ; 
			System.out.println("SingleRef.init2, calling  exit and Forcing Abort") ;
			System.exit(-1);
		}	
		return logger ;
	}
	public void  startServerlog()
	{
		logger.info("WSWS Server Started.... ");
	}
	public Logger getLogger()
	{
		
		return logger ;
	}
}
