package com.shdp.websockets.webservice.usecases;

import com.shdp.websockets.webservice.ProcessModeEnum;

public enum WebServiceUseCasesEnum {

	
	makeOneRunOneWayWS1(1,"makeOneRunOneWayWS1", ProcessModeEnum.OneWay,"One-Way WS1 Template ",false) ,  		//OneWay
	makeOneASyncnrwWS1 (2,"makeOneASyncnrwWS1",ProcessModeEnum.Asynchronous,"Asynchronous WS1 Template",false),		//Asynchronous
	makeOneSyncWS1 (3,"makeOneSyncWS1", ProcessModeEnum.Synchronous,"Synchronous WS1 Template",false) ,			//Synchronous
	makeOneASyncrEmpWS (4,"makeOneASyncrEmpWS", ProcessModeEnum.Asynchronous,"Asynchronous Employees Webservice Example",false),	//Asynchronous
	makeOneSyncEmpWS (5,"makeOneSyncEmpWS",ProcessModeEnum.Synchronous,"Synchronous Employees Webservice Example",false) ,			//Synchronous
	makeOneSyncRtrvFileWS(6,"makeOneSyncRtrvFileWS",ProcessModeEnum.Synchronous,"Synchronous Retrieve File  Webservice Example",false) 	;		//Synchronous ServiceAgreedRtrvFileSyncWS
	
	private int wsId ;		   
	private String  wsName ;
	private Boolean doIt = false ;
	private ProcessModeEnum  processMode = ProcessModeEnum.Synchronous;
	private String description = null ;;
	
	private WebServiceUseCasesEnum (int id,String wsNamei,ProcessModeEnum processModei, String descriptioni, Boolean doBoolean )
	{
		wsId=id ;
		wsName = wsNamei ;
		doIt=doBoolean ;
		processMode= processModei ;
		description= descriptioni ;
	}
	
	public String getWsName() {
		return wsName;
	}

	public void setWsName(String wsName) {
		this.wsName = wsName;
	}

	

	public int getWsId() {
		return wsId;
	}

	public Boolean getDoIt() {
		return doIt;
	}

	public ProcessModeEnum getProcessMode() {
		return processMode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
