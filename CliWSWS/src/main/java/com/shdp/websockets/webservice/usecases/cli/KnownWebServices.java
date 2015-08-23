package com.shdp.websockets.webservice.usecases.cli;

import java.util.ArrayList;
import java.util.List;

import com.shdp.websockets.webservice.ProcessModeEnum;
import com.shdp.websockets.webservice.usecases.WebServiceUseCasesEnum;

public class KnownWebServices {
	private int wsId ;	
	private String  wsName ;
	private Boolean doIt = false ;
	private ProcessModeEnum  processMode = ProcessModeEnum.Synchronous;
	private String description = null ;
	private String runId = null ;
	//wsId:6, wsName:"makeOneSyncRtrvFileWS", processMode:"Synchronous", description:"Synchronous Retrieve File  Webservice Example", doForm:true, runId:"sssssssssssss", title:"Add", form3WorkType:"Edit", $$hashKey:"00U"})
	public List<KnownWebServices> getTheLis () {
		List<KnownWebServices> knwsLis = new ArrayList<>();
		for (WebServiceUseCasesEnum wsIn : WebServiceUseCasesEnum.values())
		{
			KnownWebServices knownWebServicesi = new KnownWebServices();
			knownWebServicesi.setWsId(wsIn.getWsId());
			knownWebServicesi.setWsName(wsIn.getWsName());
			knownWebServicesi.setProcessMode(wsIn.getProcessMode());
			knownWebServicesi.setDescription(wsIn.getDescription()) ;
			knownWebServicesi.setDoIt(false);
			
			knwsLis.add(knownWebServicesi);
		}
		
		return knwsLis ;
	}
	
	public int getWsId() {
		return wsId;
	}
	public void setWsId(int wsId) {
		this.wsId = wsId;
	}
	public String getWsName() {
		return wsName;
	}
	public void setWsName(String wsName) {
		this.wsName = wsName;
	}
	public Boolean getDoIt() {
		return doIt;
	}
	public void setDoIt(Boolean doIt) {
		this.doIt = doIt;
	}
	public ProcessModeEnum getProcessMode() {
		return processMode;
	}
	public void setProcessMode(ProcessModeEnum processMode) {
		this.processMode = processMode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRunId() {
		return runId;
	}

	public void setRunId(String runId) {
		this.runId = runId;
	}

	@Override
	public String toString() {
		return "KnownWebServices [wsId=" + wsId + ", wsName=" + wsName
				+ ", doIt=" + doIt + ", processMode=" + processMode
				+ ", description=" + description + ", runId=" + runId + "]";
	}

	

}
