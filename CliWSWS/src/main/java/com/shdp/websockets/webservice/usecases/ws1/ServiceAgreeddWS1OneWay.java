package com.shdp.websockets.webservice.usecases.ws1;

import com.shdp.websockets.webservice.DefaultVoidResp;
import com.shdp.websockets.webservice.ProcessModeEnum;
import com.shdp.websockets.webservice.ProcessServerReqIF;
import com.shdp.websockets.webservice.ServiceAgreementOneWayIF;
import com.shdp.websockets.webservice.ServiceServerAnnotation;



@ServiceServerAnnotation( processModeEnum=ProcessModeEnum.OneWay,
							 reqClass=ReqWS1.class,respClass=DefaultVoidResp.class,
							 processServerReqClass=ProcessServerReqWS1OneWay.class )

//DefaultVoidResp
public class ServiceAgreeddWS1OneWay implements ServiceAgreementOneWayIF<ReqWS1,DefaultVoidResp>{ 
	
	private ReqWS1    			req ;
	
	private ProcessServerReqIF<ReqWS1,DefaultVoidResp>  processServerReq ;
	
	private String serviceURL ;
		
	public ReqWS1  getRequest() {
		return req ;
	}
	public void setRequest(ReqWS1 req11) {
		this.req = req11;
	}
	//
	public ProcessServerReqIF<ReqWS1,DefaultVoidResp> getProcessServerReq()
	{
		return processServerReq ;
	} 
	public void setProcessServerReq(ProcessServerReqIF<ReqWS1,DefaultVoidResp>  processServerReq11x) 
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
