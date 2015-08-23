package com.shdp.websockets.webservice.usecases.ws1;

import java.util.logging.Logger;

import com.shdp.websockets.webservice.ProcessServerReqIF;
//import com.shdp.websockets.webservice.SingleRef;
import com.shdp.websockets.webservice.usecases.WiredInstancesCli;

public class ProcessServerReqWS1 implements ProcessServerReqIF<ReqWS1, RespWS1 >
{
	// private final  Logger logger = Logger.getLogger(this.getClass().getName());
	private final   Logger logger = WiredInstancesCli.INSTANCE.getLogger() ;
 public void processServerReq(ReqWS1 req11, RespWS1 resp11)
 {
	
	 System.out.println("Start ProcessServerReqWS1 ....") ;
	 resp11.setRespInt1(System.identityHashCode(this));
	 resp11.setRespStr1("Server responseStr11_setfrom ProcessServerReq11");
	 resp11.setReqRespIdfromCli(req11.getReqRespIdLong());
	 logger.info("Done ...");
	 
 }
}

