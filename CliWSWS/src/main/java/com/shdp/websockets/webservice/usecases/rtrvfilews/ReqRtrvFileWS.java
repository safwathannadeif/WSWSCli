package com.shdp.websockets.webservice.usecases.rtrvfilews;

import java.util.Date;

import com.shdp.websockets.webservice.ReqAndResponseAnnotaion;
import com.shdp.websockets.webservice.usecases.WiredInstancesCli;

@ReqAndResponseAnnotaion
public class ReqRtrvFileWS 
{
String path = null ;
String filename= null ;
	
private Date dateNow = new Date() ;
private Long reqRespIdLong = WiredInstancesCli.INSTANCE.getReqRespId() ;
private String threadName ;

public Date getDateNow() {
	return dateNow;
}

public void setDateNow(Date dateNow) {
	this.dateNow = dateNow;
}

public Long getReqRespIdLong() {
	return reqRespIdLong;
}

public void setReqRespIdLong(Long reqRespIdLong) { 
	this.reqRespIdLong = reqRespIdLong;
}

public String getThreadName() {
	return threadName;
}

public void setThreadName(String threadName) {
	this.threadName = threadName;
}


public String getPath() {
	return path;
}

public void setPath(String path) {
	this.path = path;
}

public String getFilename() {
	return filename;
}

public void setFilename(String filename) {
	this.filename = filename;
}

@Override
public String toString() {
	return "ReqBigFileSyncWS [path=" + path + ", filename=" + filename
			+ ", dateNow=" + dateNow + ", reqRespIdLong=" + reqRespIdLong
			+ ", threadName=" + threadName + "]";
}


}
		
