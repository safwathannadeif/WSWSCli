package com.shdp.websockets.webservice ;

public  enum ProcessModeEnum {

   /*
    * Notes: 
    * These modes are from Application Perspective. The Actual Client Receiver Operation always **Asynchronous**  
    * This framework make the Client Receive: 
    * -1 Synchronous  : The Client-Application will blocked till receives the response
    * -2 Asynchronous : The Client-Application will Not blocked, and when the Response received it will Resume via callback
    * -3 OneWay       : The Client-Application send and will not process and Response
    */
	Asynchronous("Asynchronous", "Send, and let the Response make the callBack for the Instance Object/processClientResp with State"),
	Synchronous("Synchronous", "Send, and the caller thread forced to Wait. The Rreturn Response will resume caller thread"), 
    OneWay("OneWay", "Send, Forget It with Good Luck!") ;
   
    private String mode;
    private String description ;
    
    ProcessModeEnum(String modei,String descriptioni)
    {
    	mode = modei;
    	description = descriptioni ;
    }
	public String getMode() {
		return mode;
	}
	
	public String getDescription() {
		return description;
	}
	
   
    
   
}