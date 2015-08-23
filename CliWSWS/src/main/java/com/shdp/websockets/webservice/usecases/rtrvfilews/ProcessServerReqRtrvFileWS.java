package com.shdp.websockets.webservice.usecases.rtrvfilews;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import com.shdp.websockets.webservice.ProcessServerReqIF;
//import com.shdp.websockets.webservice.SingleRef;
import com.shdp.websockets.webservice.usecases.WiredInstancesCli;

public class ProcessServerReqRtrvFileWS implements ProcessServerReqIF<ReqRtrvFileWS , RespRtrvFileWS >
{
private final   Logger logger = WiredInstancesCli.INSTANCE.getLogger() ;

 public void processServerReq(ReqRtrvFileWS req, RespRtrvFileWS resp)
 {
	
	 System.out.println("Start ProcessServerReqBigFileWS ....") ;
	 System.out.println("Server Read Req [" + req.getPath() +"][" +  req.getFilename() +"]" ) ;
	 
	 String[] outStrAry = readFileAsStreamOfString(req.getPath(), req.getFilename()) ;
	 //
	 
	 //
	 List<String>  list = Arrays.asList(outStrAry);
	 System.out.println("Server ArrayList of String:" + list.toString());
	 //
	 resp.setArrySize(outStrAry.length);
	 resp.setServerDate(new Date());
	 resp.setList(list);
	 
	 
 }
 private String[]  readFileAsStreamOfString (String path, String fileName) 
 {
	String[] arrayOfStrs = null ; 
    try
    {
    	Stream<String> lines = Files.lines(Paths.get(path, fileName));
    	arrayOfStrs  = lines.toArray(String[]::new);
    	lines.close();
    }
    catch(IOException ex)
    {
    	ex.printStackTrace();
    	logger.throwing("ProcessServerReqBigFileWS", "readFileAsStreamOfString", ex);
    	return null ;
    }
   
    return arrayOfStrs ;
  
 }

}
