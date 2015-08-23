package com.shdp.websockets.webservice.usecases.rtrvfilews;

import java.util.Date;
import java.util.List;

import com.shdp.websockets.webservice.ReqAndResponseAnnotaion;

@ReqAndResponseAnnotaion
public class RespRtrvFileWS {

private Integer arrySize  ;
private List<String>  list ;

private Date serverDate = new Date() ; 
public Integer getArrySize() {
	return arrySize;
}
public void setArrySize(Integer arrySize) {
	this.arrySize = arrySize;
}

public Date getServerDate() {
	return serverDate;
}
public void setServerDate(Date serverDate) {
	this.serverDate = serverDate;
}
public List<String> getList() {
	return list;
}
public void setList(List<String> list) {
	this.list = list;
}
@Override
public String toString() {
	return "RespBigFileWS [arrySize=" + arrySize + ", list=" + list
			+ ", serverDate=" + serverDate + "]";
}


 
}