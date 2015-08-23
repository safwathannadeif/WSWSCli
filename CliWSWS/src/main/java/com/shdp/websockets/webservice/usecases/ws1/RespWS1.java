package com.shdp.websockets.webservice.usecases.ws1;

import java.util.Date;

import com.shdp.websockets.webservice.ReqAndResponseAnnotaion;

@ReqAndResponseAnnotaion
public class RespWS1 {
private String respStr1 ;
private Integer respInt1 ;
private Date serverDate = new Date() ;
private Long reqRespIdfromCli ;
private String tstRespStr ;
 

public String tstStr() {
	return "Resp11 [respStr1=" + respStr1 + ", respInt1=" + respInt1 + "serverDate=" +serverDate +"]";
}


public String getRespStr1() {
	return respStr1;
}
public void setRespStr1(String respStr1) {
	this.respStr1 = respStr1;
}
public Integer getRespInt1() {
	return respInt1;
}
public void setRespInt1(Integer respInt1) {
	this.respInt1 = respInt1;
}

public Date getServerDate() {
	return serverDate;
}

public void setServerDate(Date serverDate) {
	this.serverDate = serverDate;
}

public Long getReqRespIdfromCli() {
	return reqRespIdfromCli;
}

public void setReqRespIdfromCli(Long reqRespIdfromCli) {
	this.reqRespIdfromCli = reqRespIdfromCli;
}


public String getTstRespStr() {
	return tstStr();
}


public void setTstRespStr(String tstRespStr) {
	this.tstRespStr = tstRespStr;
}


@Override
public String toString() {
	return "Resp11 [respStr1=" + respStr1 + ", respInt1=" + respInt1
			+ ", serverDate=" + serverDate + ", reqRespIdfromCli="
			+ reqRespIdfromCli + ", tstRespStr=" + tstRespStr + "]";
}




}