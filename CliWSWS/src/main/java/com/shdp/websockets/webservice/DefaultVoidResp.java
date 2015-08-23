package com.shdp.websockets.webservice;

//Just Holder for default ReqAndResp
//public class DefaultRespNoUse implements  ReqAndRespIF<Void> {
public class DefaultVoidResp  {
	

DefaultVoidResp()
{
	throw  new RuntimeException ("DefaultRespNoUse CAN NOT Be Used") ;
}
@Override
public String toString() {
	return null ;
}
public Void  ConvertToPOJO(String jsonStr) 
{
	return null;
	
}
public String ConvertToJsonStr() 
{
	return null ;
}

}