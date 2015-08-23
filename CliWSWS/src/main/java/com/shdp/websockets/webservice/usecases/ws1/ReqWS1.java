package com.shdp.websockets.webservice.usecases.ws1;

import java.util.Date;

import com.shdp.websockets.webservice.ReqAndResponseAnnotaion;
import com.shdp.websockets.webservice.usecases.WiredInstancesCli;

@ReqAndResponseAnnotaion
public class ReqWS1 
{

private String reqStr1 ;
private Integer reqInt1 ;
private Date dateNow = new Date() ;
private String curStr ;
private Long reqRespIdLong = WiredInstancesCli.INSTANCE.getReqRespId() ;
private String threadName ;

public String getReqStr1() {
	return reqStr1;
}

public void setReqStr1(String reqStr1) {
	this.reqStr1 = reqStr1;
}

public Integer getReqInt1() {
	return reqInt1;
}

public void setReqInt1(Integer reqInt1) {
	this.reqInt1 = reqInt1;
}


public Date getDateNow() {
	return dateNow;
}

public void setDateNow(Date dateNow) {
	this.dateNow = dateNow;
}



public String reqString() {
	return "Req11 [reqStr1=" + reqStr1 + ", reqInt1=" + reqInt1 + ", dateNow="
			+ dateNow + ", getReqStr1()=" + getReqStr1() + ", getReqInt1()="
			+ getReqInt1() + ", getDateNow()=" + getDateNow() + ", getClass()="
			+ getClass() + ", hashCode()=" + hashCode() + ", toString()="
			+ super.toString() + "]";
}



public Long getReqRespIdLong() {
	return reqRespIdLong;
}

public void setReqRespIdLong(Long reqRespIdLong) {
	this.reqRespIdLong = reqRespIdLong;
}



public String getCurStr() {
	return curStr;
}

public void setCurStr(String curStr) {
	this.curStr = curStr;
}

public String getThreadName() {
	return threadName;
}

public void setThreadName(String threadName) {
	this.threadName = threadName;
}

@Override
public String toString() {
	return "Req11 [reqStr1=" + reqStr1 + ", reqInt1=" + reqInt1 + ", dateNow="
			+ dateNow + ", curStr=" + curStr + ", reqRespIdLong="
			+ reqRespIdLong + ", threadName=" + threadName + "]";
}


}
		
