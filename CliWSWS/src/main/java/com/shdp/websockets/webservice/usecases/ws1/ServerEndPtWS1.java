package com.shdp.websockets.webservice.usecases.ws1;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;

import com.shdp.websockets.webservice.ServerReceiveSend;
import com.shdp.websockets.webservice.WiredInstances;

@ServerEndpoint(
value = "/websocketPathWS1"
)
public class ServerEndPtWS1 {
	// private final  Logger logger = Logger.getLogger(this.getClass().getName());
	private final   Logger logger = WiredInstances.INSTANCE.getLogger() ;
	
@OnMessage
public void onMessage(String recvTxt, Session session) throws JsonParseException, JsonMappingException, NumberFormatException, ClassNotFoundException, InstantiationException, IllegalAccessException, IOException
{
		  ServerReceiveSend serverSendReceive = new ServerReceiveSend(session) ;
		  serverSendReceive.onReceive(recvTxt); 
}
@OnOpen
public void onOpen(Session session) {
 System.out.println("ServeronOpen ..... sessionId=" + session.getId() );
}

@OnClose
public void  onClose(Session session, CloseReason cReason)
{
	  System.out.println ("Server closed: " +  ", session:" + session.getId() + ", CloseReason:" +cReason);
	  logger.severe("Server closed: " +  ", session:" + session.getId() + ", CloseReason:" +cReason);
}
@OnError 
public void  error(Throwable t, Session session)
 {
	  System.out.println ("Server closed: " +  ", session:" + session.getId() + ", Throwable:" +t.getMessage());
	  logger.severe("Server closed: " +  ", session:" + session.getId() + ", Throwable:" +t.getMessage());
	  t.printStackTrace();
	  logger.throwing(ServerEndPtWS1.class.getName(), "error", t);
	  
}
}