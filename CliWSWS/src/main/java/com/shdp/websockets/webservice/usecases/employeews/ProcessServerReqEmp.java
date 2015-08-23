package com.shdp.websockets.webservice.usecases.employeews;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import com.shdp.websockets.webservice.ProcessServerReqIF;
import com.shdp.websockets.webservice.WiredInstances;
//import com.shdp.websockets.webservice.SingleRef;

public class ProcessServerReqEmp implements ProcessServerReqIF<ReqEmp, RespEmp >

{
	 private final  Logger logger = WiredInstances.INSTANCE.getLogger() ;
			
 public void processServerReq(ReqEmp reqEmp, RespEmp respEmp)
 {
	
	 System.out.println("Start ProcessServerReqEmp ....") ;	
			
			//Create the employees
			Employee emp1 = new Employee(1, "Ollie", "Jenson", "M", 26,EvalRates.Excellent, new BigDecimal(100000.100));
			Employee emp2 = new Employee(2, "Lyra", "Leighton", "F", 60,EvalRates.VeryGood, new BigDecimal(200000.200));
			Employee emp3 = new Employee(3, "Freddie", "Mike", "M", 34,EvalRates.Unknown, new BigDecimal(300000.300));
			Employee emp4 = new Employee(4, "Zach", "Jacob", "M", 56,EvalRates.Good, new BigDecimal(400000.400));
			Employee emp5 = new Employee(5, "Angus", "Jackson", "M", 43,EvalRates.Unknown, new BigDecimal(500000.500));
			Employee emp6 = new Employee(6, "Olivia", "kelsey", "F", 29,EvalRates.Excellent, new BigDecimal(400200.400));
			Employee emp7 = new Employee(7, "Arthur", "Jenson", "M", 27,EvalRates.Excellent, new BigDecimal(300200.433));
			Employee emp8 = new Employee(8, "Harriet", "Leighton", "F", 43,EvalRates.VeryGood, new BigDecimal(200400.230));
			Employee emp9 = new Employee(9, "Rowan", "Darcy", "M", 38,EvalRates.Unknown,new BigDecimal(500400.550));
			Employee emp10 = new Employee(10, "Milo", "Tom", "M", 54,EvalRates.Good, new BigDecimal(500400.550));
			Employee emp11 = new Employee(11, "Stanley", "kelsey", "M", 29,EvalRates.Unknown,new BigDecimal(200400.550));
			Employee emp12= new Employee(12, "Nancy", "Troy", "F", 46,EvalRates.Excellent, new BigDecimal(400400.550));
			Employee emp13 = new Employee(13, "Jenson", "Ivy", "M", 39,EvalRates.Excellent, new BigDecimal(700700.570));
			Employee emp14 = new Employee(14, "Harriet", "Willow", "F", 40,EvalRates.VeryGood, new BigDecimal(600600.660));
			Employee emp15 = new Employee(15, "Penelope", "Tallulah", "M", 37,EvalRates.Unknown,new BigDecimal(300600.360));
			Employee emp16 = new Employee(16, "Rae", "Dave", "M", 65,EvalRates.Good, new BigDecimal(800600.80860));
			Employee emp17 = new Employee(17, "Stanley", "kelsey", "M", 55,EvalRates.Unknown,new BigDecimal(300600.30460));
			Employee emp18= new Employee(18, "mAii", "Troy", "F", 29,EvalRates.Excellent,new BigDecimal(500600.50860));
			//Create a new list of emp objects
			List<Employee> emps = new ArrayList<Employee>();
			emps.add(emp1);
			emps.add(emp2);
			emps.add(emp3);
			emps.add(emp4);
			emps.add(emp5);
			emps.add(emp6);
			emps.add(emp7);
			emps.add(emp8);
			emps.add(emp9);
			emps.add(emp10);
			emps.add(emp11);
			emps.add(emp12);
			emps.add(emp13);
			emps.add(emp14);
			emps.add(emp15);
			emps.add(emp16);
			emps.add(emp17);
			emps.add(emp18);
			
	respEmp.setEmplisResp(emps);
	respEmp.setReqTo(reqEmp.getReqBy());
	respEmp.setRespWhen(new Date());
	respEmp.setReqId(reqEmp.getReqId());
	System.out.println("End ProcessServerReqEmp ....") ;
	logger.info("ProcessServerReqEmp Done ...");
	 
 }
}