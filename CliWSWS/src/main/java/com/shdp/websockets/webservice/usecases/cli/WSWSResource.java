package com.shdp.websockets.webservice.usecases.cli;

import static spark.Spark.get;
import static spark.Spark.post ;

/**
 *
 */
public class WSWSResource {


    private static final String TST_URI = "/wsws/tst" ;
    private static final String LisAvalWSWS_URI = "/wsws/lisAvlWSWS" ;
    private static final String PRE_WSWS_TEST_URI  = "/wsws/preWSWSTest" ;
    private static final String RUN_WSWS_TEST_URI  = "/wsws/runWSWSTest" ;
    private static final String TEST2_ONLY_URI  = "/wsws/servTest2" ;
    
 
     private final WSWSCliServices  wSWSServices ;


public WSWSResource()
{
    	 wSWSServices= new WSWSCliServices() ;
    	 mapSparkEndpoints() ;
}
    
    public  void mapSparkEndpoints() { 
    	/*get("/", (request, response) -> {
            response.redirect("/index.html");
            return "";
        });*/
    	get(TST_URI, "application/json", (request, response)
                -> wSWSServices.tst(), new JsonTransformer()); 
    	
    	get(LisAvalWSWS_URI, "application/json", (request, response)
                -> wSWSServices.listAvlWSWS(), new JsonTransformer()); 
    	
    	get(PRE_WSWS_TEST_URI, "application/json", (request, response)
                -> wSWSServices.preDoWSWSTest(), new JsonTransformer()); 
    	
    	post(RUN_WSWS_TEST_URI, "application/json", (request, response) -> {
    				wSWSServices.runWSWSTest(request.body());
    	            response.status(201);
    	            return response;
    	        }, new JsonTransformer());
    	
    	get(TEST2_ONLY_URI, "application/json", (request, response)
                -> wSWSServices.test2(), new JsonTransformer()); 
    }

}
/*	AngularJS Mapping:
* 	URI Service Resource  "/wsws/tst":
*  		ng::wsAngApp.js: Glue the Viewer/templateUrl-value and the Controller-value
*   	templateUrl: 'views/tst.html', controller: 'tstCtrl' under URI browser input "/" [#/]
*   
*   URI Service Resource  "/wsws/LisAvlWSWS":
*   	ng::wsAngApp.js: Glue the Viewer/templateUrl-value and the Controller-value
*   	templateUrl: 'views/lisAvlWSWS.html', controller: 'lisAvlWSWSCtrl' under URI browser input "/lisAvlWSWS" [#/lisAvlWSWS]
*/