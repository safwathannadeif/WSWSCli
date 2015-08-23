package com.shdp.websockets.webservice.usecases.employeews;

import com.shdp.websockets.webservice.ProcessClientRespIF;

public class ProcessClientRespEmp implements ProcessClientRespIF<RespEmp>
{
	//private final static Logger logger = Logger.getLogger(ProcessClientRespEmp.class.getName());
	public void processClientResp(RespEmp respEmp)
	{
		System.out.println("Start processClientResp ....") ;
		System.out.println("respEmp.getReqTo:" + respEmp.getReqTo()) ;
		System.out.println("respEmp.getRespWhen:" + respEmp.getRespWhen() ) ;
		for (Employee emp  : respEmp.getEmpLisResp()) 
		{
			System.out.println( emp.toString() );
		}
		System.out.println("End processClientResp ....") ;	
	}
}