package com.shdp.websockets.webservice.usecases.ws1;

import java.io.IOException;
import java.net.InetAddress;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/echo")
public class TestEcho {
private Boolean serverInfo = false ;
    @OnMessage
    public void echoTextMessage(Session session, String msg, boolean last) {
        try {
        	String computerName = InetAddress.getLocalHost().getHostName() ;
        	if (session.isOpen()) {
        		if ( !serverInfo) {
        			 session.getBasicRemote().sendText("Server URL: [" + computerName+":"+ session.getRequestURI() +"]") ;
        			 serverInfo =true ;
        		}
               
                session.getBasicRemote().sendText("Server Echo:" +msg, last) ;
                
            }
        } catch (IOException e) {
            try {
                session.close();
            } catch (IOException e1) {
                // Ignore
            }
        }
    }


}