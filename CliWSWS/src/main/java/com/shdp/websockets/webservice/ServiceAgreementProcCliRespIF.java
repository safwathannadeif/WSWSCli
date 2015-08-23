package com.shdp.websockets.webservice;

public interface ServiceAgreementProcCliRespIF<TResp> {
	//Needed Only For Asyncr
			public ProcessClientRespIF<TResp>        	getProcessClientResp() ;
			public void									setProcessClientResp(ProcessClientRespIF<TResp> processClientResp) ;

}
