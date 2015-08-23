package com.shdp.websockets.webservice;
/*
* To provides a consistency instances view between the client and server and forcing the development:
*    Client Request Object == Server Request Object   &
*	 Server Response Object  == Client Response Object  
*
*/
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface ServiceServerAnnotation {

 public ProcessModeEnum processModeEnum() default ProcessModeEnum.Synchronous ;			//Option
  
  public Class<?> reqClass()  ;     														//Mandatory request class
  public Class<?> respClass() default DefaultVoidResp.class;								//Option since no response for oneWay
  // TODO We should add the Error List Accessories TODO
 // public Class<? extends ProcessClientRespIF<?>>  processCliRespClass()   ;					//Option 
  public Class<? extends ProcessServerReqIF<?,?>> processServerReqClass() ;  				//Mandatory server processing request class
}
