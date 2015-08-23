package com.shdp.websockets.webservice.usecases.ws1;

import com.shdp.websockets.webservice.ProcessClientRespIF;
import com.shdp.websockets.webservice.ProcessModeEnum;
import com.shdp.websockets.webservice.ProcessServerReqIF;
import com.shdp.websockets.webservice.ServiceAgreementASyncrsIF;
import com.shdp.websockets.webservice.ServiceServerAnnotation;

@ServiceServerAnnotation( processModeEnum=ProcessModeEnum.Asynchronous,
							 reqClass=ReqWS1.class,respClass=RespWS1.class,
							 processServerReqClass=ProcessServerReqWS1.class)
				

public class ServiceAgreedWS1Asyncr implements  ServiceAgreementASyncrsIF<ReqWS1,RespWS1>{
	
	private ReqWS1    req ;
	private RespWS1   resp ;
	
	
	private ProcessServerReqIF<ReqWS1,RespWS1>  processServerReq ;
	private ProcessClientRespIF<RespWS1>    processClientResp ;
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
	public  ProcessClientRespIF<RespWS1> getProcessClientResp() //callback 
	{
		return processClientResp ;
	}
	public void setProcessClientResp(ProcessClientRespIF<RespWS1>  processClientRespx)
	{
		processClientResp=processClientRespx ;
	}
	//
	public ProcessServerReqIF<ReqWS1,RespWS1> getProcessServerReq()
	{
		return processServerReq ;
	} 
	public void setProcessServerReq(ProcessServerReqIF<ReqWS1,RespWS1>  processServerReq11x) 
	{
		processServerReq = processServerReq11x ;
	}
	//
	public String getServiceURL() {
		return serviceURL;
	}
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}
	
}
