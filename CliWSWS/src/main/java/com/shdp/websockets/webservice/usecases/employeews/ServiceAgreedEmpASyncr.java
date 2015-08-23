package com.shdp.websockets.webservice.usecases.employeews;


import com.shdp.websockets.webservice.ProcessClientRespIF;
import com.shdp.websockets.webservice.ProcessModeEnum;
import com.shdp.websockets.webservice.ServiceAgreementASyncrsIF;
import com.shdp.websockets.webservice.ServiceServerAnnotation;

@ServiceServerAnnotation( processModeEnum=ProcessModeEnum.Asynchronous ,
						reqClass=ReqEmp.class,
						respClass=RespEmp.class,
						processServerReqClass=ProcessServerReqEmp.class)


public class ServiceAgreedEmpASyncr implements  ServiceAgreementASyncrsIF<ReqEmp,RespEmp>  {
	
	private ReqEmp    req ;
	private RespEmp   resp ;
	private ProcessClientRespIF<RespEmp>    processClientResp ;
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
	public  ProcessClientRespIF<RespEmp> getProcessClientResp() //callback 
	{
		return processClientResp ;
	}
	public void setProcessClientResp(ProcessClientRespIF<RespEmp>  processClientRespx)
	{
		processClientResp=processClientRespx ;
	}
	//
	public String getServiceURL() {
		return serviceURL;
	}
	public void setServiceURL(String serviceURL) {
		this.serviceURL = serviceURL;
	}
	
}
