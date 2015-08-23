package com.shdp.websockets.webservice.usecases.ws1;

import java.util.logging.Logger;

import com.shdp.websockets.webservice.DefaultVoidResp;
import com.shdp.websockets.webservice.ProcessServerReqIF;
//import com.shdp.websockets.webservice.SingleRef;
import com.shdp.websockets.webservice.usecases.WiredInstancesCli;

public class ProcessServerReqWS1OneWay implements ProcessServerReqIF<ReqWS1, DefaultVoidResp >
{
	// private final static Logger logger = Logger.getLogger(ProcessClientRespWS1.class.getName());
	private final   Logger logger = WiredInstancesCli.INSTANCE.getLogger() ;
 public void processServerReq(ReqWS1 req11, DefaultVoidResp noResp)
 {
	 logger.info ("ProcessServerReq11OneWay req11.getReqRespIdLong()) ="  + req11.getReqRespIdLong());
	 logger.info("Done ...");
	 
 }
}
