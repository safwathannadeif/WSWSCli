package com.shdp.websockets.webservice.usecases.employeews;

import java.util.Date;
import java.util.List;

import com.shdp.websockets.webservice.ReqAndResponseAnnotaion;

//@ReqAndResponseAnnotaion
@ReqAndResponseAnnotaion (jsonConverter=EmpRespJsonConvert.class)
public class RespEmp 
{
private Long reqId = null  ;
private String reqTo ;
private Date respWhen= new Date() ;
private  List<Employee> empLisResp ;
public String getReqTo() {
	return reqTo;
}
public void setReqTo(String reqTo) {
	this.reqTo = reqTo;
}
public Date getRespWhen() {
	return respWhen;
}
public void setRespWhen(Date respWhen) {
	this.respWhen = respWhen;
}
public List<Employee> getEmpLisResp() {
	return empLisResp;
}
public void setEmplisResp(List<Employee> emplisResp) {
	this.empLisResp = emplisResp;
}

public Long getReqId() {
	return reqId;
}
public void setReqId(Long reqId) {
	this.reqId = reqId;
}
@Override
public String toString() {
	return "RespEmp [reqId=" + reqId + ", reqTo=" + reqTo + ", respWhen="
			+ respWhen + "\n, empLisResp=" + empLisResp + "]";
}



}
