package com.shdp.websockets.webservice.usecases.rtrvfilews;


import com.shdp.websockets.webservice.ProcessModeEnum;
import com.shdp.websockets.webservice.ServiceAgreementSyncIF;
import com.shdp.websockets.webservice.ServiceServerAnnotation;

@ServiceServerAnnotation( processModeEnum=ProcessModeEnum.Synchronous,
							 reqClass=ReqRtrvFileWS.class ,
							 respClass=RespRtrvFileWS.class,
							 processServerReqClass=ProcessServerReqRtrvFileWS.class)
	
public class ServiceAgreedRtrvFileSyncWS implements  ServiceAgreementSyncIF<ReqRtrvFileWS,RespRtrvFileWS> { 
	
	private ReqRtrvFileWS    req ;
	private RespRtrvFileWS   resp ;
	private String serviceURL ;
		
	public ReqRtrvFileWS  getRequest() {
		return req ;
	}
	public void setRequest(ReqRtrvFileWS req) {
		this.req = req;
	}
	
	//
	public RespRtrvFileWS getResponse() {
		return resp;
	}
	//
	public void  setResponse(RespRtrvFileWS  respx) {
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
