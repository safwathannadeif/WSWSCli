package com.shdp.websockets.webservice.usecases.employeews;

import java.util.Date;

import com.shdp.websockets.webservice.ReqAndResponseAnnotaion;
import com.shdp.websockets.webservice.usecases.WiredInstancesCli;

@ReqAndResponseAnnotaion
public class ReqEmp 
{
private String reqBy ;
private Date reqWhen= new Date() ;
private Long reqId = WiredInstancesCli.INSTANCE.getReqRespId() + 1000 ; 
public String getReqBy() {
	return reqBy;
}
public void setReqBy(String reqBy) {
	this.reqBy = reqBy;
}
public Date getReqWhen() {
	return reqWhen;
}
public void setReqWhen(Date reqWhen) {
	this.reqWhen = reqWhen;
}
public Long getReqId() {
	return reqId;
}

}
