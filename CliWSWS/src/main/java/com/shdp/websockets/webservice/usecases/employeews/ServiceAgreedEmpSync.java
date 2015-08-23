package com.shdp.websockets.webservice.usecases.employeews;



import com.shdp.websockets.webservice.ProcessModeEnum;
import com.shdp.websockets.webservice.ServiceAgreementSyncIF;
import com.shdp.websockets.webservice.ServiceServerAnnotation;

@ServiceServerAnnotation( processModeEnum=ProcessModeEnum.Synchronous ,
						reqClass=ReqEmp.class,respClass=RespEmp.class,
						processServerReqClass=ProcessServerReqEmp.class)


public class ServiceAgreedEmpSync implements  ServiceAgreementSyncIF<ReqEmp,RespEmp>{
	
	private ReqEmp    req ;
	private RespEmp   resp ;
	private String serviceURL ;
		
	public ReqEmp  getRequest() {
		return req ;
	}
	public void setRequest(ReqEmp reqEmp) {
		this.req = reqEmp;
	}
	
	//
	public RespEmp getResponse() {
		return resp;
	}
	//
	public void  setResponse(RespEmp  respx) {
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
