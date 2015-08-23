package com.shdp.websockets.startup;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.shdp.websockets.webservice.WiredInstances;
 
// @WebListener annotation informs container that
// this class is a web listener which will listen to
// various events happening during lifecycle of application
// Here this class listens to StartUp and ShutDown of the
// application.
 
// We make class implements ServletContextListener which has two
// methods contextInitialized() and contextDestroyed() , which
// are called by the container whenever a servlet context is
// started or shutdown
 
@WebListener
public class ListenerStartup4InitConfig implements ServletContextListener {
 
 public void contextInitialized(ServletContextEvent servletContextEvent) {
 
	 WiredInstances.INSTANCE.startServerlog();
	 System.out.println("ListenerStartup4InitConfig init Done");
 
 
 }
 
 public void contextDestroyed(ServletContextEvent servletContextEvent) {
	 System.out.println("ListenerStartup4InitConfig Destroyed Context  Done");
	
 
 }
 
}
