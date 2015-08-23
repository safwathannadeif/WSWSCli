package com.shdp.websockets.webservice;

//Basic Elements for All Services
public interface ServiceAgreementBaseIF<TReq,TResp>   {

	public   TReq 								getRequest() ;
	public   void   							setRequest(TReq treq) ;
		
	public String 								getServiceURL() ;
	public void 								setServiceURL(String serverURL) ;
	//TODO error List 
}