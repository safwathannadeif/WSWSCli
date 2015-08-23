package com.shdp.websockets.webservice;

public interface ProcessServerReqIF<Req, Resp> {
	
	void processServerReq(Req req, Resp resp);

}