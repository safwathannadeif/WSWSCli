package com.shdp.websockets.webservice.usecases.ws1  ;

import com.shdp.websockets.webservice.ProcessModeEnum;
import com.shdp.websockets.webservice.ServiceAgreementSyncIF;
import com.shdp.websockets.webservice.ServiceServerAnnotation;

@ServiceServerAnnotation( processModeEnum=ProcessModeEnum.Synchronous,
							 reqClass=ReqWS1.class,respClass=RespWS1.class,
							 processServerReqClass=ProcessServerReqWS1.class)


public class ServiceAgreedWS1Sync implements  ServiceAgreementSyncIF<ReqWS1,RespWS1> {
	
	private ReqWS1    req ;
	private RespWS1   resp ;
	
	

	
	private String serviceURL ;
		
	public ReqWS1  getRequest() {
		return req ;
	}
	public void setRequest(ReqWS1 req11) {
		this.req = req11;
	}
	
	//
	public RespWS1 getResponse() {
		return resp;
	}
	//
	public void  setResponse(RespWS1  respx) {
		 resp = respx;
	}
	
	
	//
	public String getServiceURL() {
		return serviceURL;
	}
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}
	
}
