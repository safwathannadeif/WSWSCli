package com.shdp.websockets.webservice.usecases.cli;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class WSWSCliServices {

public List<String> tst()
{
	List<String> strsLis = new ArrayList<>();
	strsLis.add("String1....") ;
	strsLis.add("String2....") ;
	return strsLis ;
}

public List<KnownWebServices> listAvlWSWS()
{
	KnownWebServices knownWebServices = new KnownWebServices();
	List<KnownWebServices> wswsAvlLis = knownWebServices.getTheLis() ;
	return wswsAvlLis ;
}
public List<KnownWebServices> preDoWSWSTest()
{
	KnownWebServices knownWebServices = new KnownWebServices();
	List<KnownWebServices> wswsPreTestLis = knownWebServices.getTheLis() ;
	return wswsPreTestLis ;
}
//
public void runWSWSTest(String body)
{
	System.out.println("Body runWSWSTest Inp[" + body.toString()+"]") ;
	Gson gson = new Gson();
	List<KnownWebServices> lisOfServcs = gson.fromJson(body, new TypeToken<List<KnownWebServices>>(){}.getType());
	if (null == lisOfServcs ) System.out.println(" lisOfServcs is NUL!!!!!!!!!!!!!!") ;
	//names.forEach(name -> System.out.println(name));
	lisOfServcs.forEach( servc -> System.out.println(servc.toString()) ) ; 
	
	System.out.println("***Test Done....");//post 
	
}
public List<String> test2()
{
	List<String> strsLis = new ArrayList<>();
	strsLis.add("String1Test2....") ;
	strsLis.add("String2Test2....") ;
	strsLis.add("String3Test2....") ;
	strsLis.add("String4Test2....") ;
	System.out.println("[/wsws/servTest2 Server function Test2 Done....");
	return strsLis ;
}
	
}
