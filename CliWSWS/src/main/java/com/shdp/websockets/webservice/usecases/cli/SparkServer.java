package com.shdp.websockets.webservice.usecases.cli;

import static spark.SparkBase.staticFileLocation;
import spark.SparkBase;


// REST	http://localhost:8888/#/
public class SparkServer    {
	//private final   Logger logger = SingleRefUseCases.INSTANCE.getLogger() ;
	//	String servURL = "ws://localhost:8080/websockets-ws/websocketPathWS1" ; //test connect 
	//	String servURL = "ws://localhost:8080/websockets-ws/test1.html" ;       //test webService 
	//  String bigFileURL = "ws://localhost:8080/websockets-ws/websocketRtrvFileWS" ; //BigFileTesting End Pt
	// 
	//Cli: http://localhost:8888/#/ ,  http://localhost:8888/wsws/LisAvlWSWS
	private static final String SPARK_IP_ADDRESS =  "localhost";
    private static final int SPARK_PORT =  8888;
    
    public static void main(String[] args) throws Exception {
		//Setup Spark and start the Server 
		SparkBase.port(SPARK_PORT);
		SparkBase.ipAddress( SPARK_IP_ADDRESS ); 
        SparkBase.staticFileLocation("/public");
        //Setting the URI Active Resources for the Spark Server
        new WSWSResource() ;
	}
}

