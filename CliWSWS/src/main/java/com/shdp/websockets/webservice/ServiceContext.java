package com.shdp.websockets.webservice;



public class ServiceContext 
{
 private String reqRespLinkStrId = null ;
 private String serviceAgreementClassName = null ;
 private String workerThreadName = null ;
 private String  workerSessionId = null ; 
 private ProcessModeEnum mode = null ;
 private String serviceURL = null ;

public String getServiceAgreementClassName() {
	return serviceAgreementClassName;
}

public void setServiceAgreementClassName(String serviceAgreementClassName) {
	this.serviceAgreementClassName = serviceAgreementClassName;
}

public ProcessModeEnum getMode() {
	return mode;
}

public void setMode(ProcessModeEnum mode) {
	this.mode = mode;
}

public String getReqRespLinkStrId() {
	return reqRespLinkStrId;
}

public void setReqRespLinkStrId(String reqRespLinkStrId) {
	this.reqRespLinkStrId = reqRespLinkStrId;
}


public String getWorkerThreadName() {
	return workerThreadName;
}

public void setWorkerThreadName(String workerThreadName) {
	this.workerThreadName = workerThreadName;
}

public String getWorkerSessionId() {
	return workerSessionId;
}

public void setWorkerSessionId(String workerSessionId) {
	this.workerSessionId = workerSessionId;
}

public String getServiceURL() {
	return serviceURL;
}

public void setServiceURL(String serviceURL) {
	this.serviceURL = serviceURL;
}

@Override
public String toString() {
	return "ServiceContext [reqRespLinkStrId=" + reqRespLinkStrId
			+ ", serviceAgreementClassName=" + serviceAgreementClassName
			+ ", workerThreadName=" + workerThreadName + ", workerSessionId="
			+ workerSessionId + ", mode=" + mode + ", serviceURL=" + serviceURL
			+ "]";
}



}
