/*package com.shdp.websockets.webservice;

public interface MaskIFs {
//
public interface NoGetRespIF<TReq,TResp> 
{
	public default TResp getResponse()
	{
		throw new RuntimeException("getResponse Not Allowed in this Service Context") ; 
	}
}
///
public interface NoSetRespIF<TReq,TResp> 
{
	public default void setResponse(TResp tresp) 
	{
		throw new RuntimeException("setResponse Not Allowed in this Service Context") ; 
	}
}
//
public interface NoGetProcessClientRespIF<TReq,TResp>  
{
	public default ProcessClientRespIF<TResp>   getProcessClientResp()  
	{
		throw new RuntimeException("getProcessClientResp Not Allowed in this Service Context") ; 
	}
}
//
public interface NoSetProcessClientRespIF<TReq,TResp>  
{
	public default void setProcessClientResp(ProcessClientRespIF<TResp> processClientResp) 
	{
		throw new RuntimeException("setProcessClientResp Not Allowed in this Service Context") ; 
	}
}

}
public interface NoGetResponeForOneWayWS<TResp> {


	public default DefaultVoidResp getResponse()    
	{
		throw new RuntimeException("getResponse Not Allowed in this Service Context") ; 
	}
}

*/