package com.shdp.websockets.webservice.usecases.ws1;

import java.util.logging.Logger;

import com.shdp.websockets.webservice.ProcessClientRespIF;

public class ProcessClientRespWS1 implements ProcessClientRespIF<RespWS1>
{
	private final static Logger logger = Logger.getLogger(ProcessClientRespWS1.class.getName());
	public void processClientResp(RespWS1 resp11)
	{
		logger.info("ProcessClientResp11 from ProcessClientRespWS1 Resp.toString \n" +resp11.toString());
	}
}