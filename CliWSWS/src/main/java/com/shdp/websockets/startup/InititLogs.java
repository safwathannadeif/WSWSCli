package com.shdp.websockets.startup;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
//import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.shdp.websockets.webservice.usecases.LogFormat;

public class InititLogs {


public static  Logger logInit2(String workLogFileName,String nameId) 
{
	Logger logger = null ;
try {
	
	//System.out.println("Log File=[" + workLogFileName + "]" ) ;

	//Remove all the Handlers 
	logger = Logger.getLogger(nameId); //Server | Client
	logger.setUseParentHandlers(false) ;//No Console
	Handler[] handlers = logger.getHandlers();
	for(Handler handler : handlers) {
		logger.removeHandler(handler);
	}
	logger.setUseParentHandlers(false) ;//No Console
	//logger = LogManager.getLogManager().getLogger(nameId);
	//logger.setUseParentHandlers(false) ;//No Console
	//java.util.logging.ConsoleHandler.level = NONE ;
	// Construct a default FileHandler.
	Handler fh;

	fh = new FileHandler(workLogFileName,600*1024,4,false);

	// append is false and 4 files for roll

	fh.setFormatter(new LogFormat());  // Set the  Logformat
//////////////////////////////////"
	
	// Add the FileHander to the logger.
	logger.addHandler(fh);
	// Set the logger level to produce logs at this level and above.
	logger.setLevel(Level.FINE);

	//System.out.println("Working Directory = " +
	//       System.getProperty("user.dir"));
	// Redirecting System.out and System.err
	/*PrintStream outPS =
new PrintStream(
new BufferedOutputStream(
new FileOutputStream(logFileName, true)));  // append is true
System.setErr(outPS);    // redirect System.err
System.setOut(outPS);*/
}
	catch (SecurityException | IOException e) {
	e.printStackTrace();
	System.out.println("Problem Setting the logging logFileName:[" + workLogFileName +"]" ) ; 
	System.out.println("InititLogs.logInit2, calling  exit and Forcing Abort") ;
	System.exit(-1);
	}
return logger ;
}
}