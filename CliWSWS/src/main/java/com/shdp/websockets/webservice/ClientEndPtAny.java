package com.shdp.websockets.webservice ;
import java.io.IOException;
import java.net.URI;
import java.util.logging.Logger;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;



@ClientEndpoint
	public class ClientEndPtAny {
		private String cliEndPtName ;
		private String serverURL ;
		private Session cliSession ;
		private ClientSendReceive clientSendReceive ;
		private final   Logger logger = WiredInstances.INSTANCE.getLogger() ;	
		
	public void init2(String serverURLCase, String cliEndPtNamei, ClientSendReceive clientSendReceivei) throws DeploymentException, IOException 
		{
			WebSocketContainer container = ContainerProvider.getWebSocketContainer();
			
	        cliSession = container.connectToServer(this, URI.create(serverURLCase) ) ; 
	        setClientSendReceive(clientSendReceivei);
	        clientSendReceivei.setClientEndPt(this);
	        setServerURL(serverURLCase) ;
	        setCliEndPtName(cliEndPtNamei);
	        System.out.println ("Opened ClientEndPtAny : " +  ", session:" + getCliSession().getId() +"/" + getServerURL() );
	        
		}   
	  @OnMessage
	  public void onMessage(String recvAllTxt, Session session) 
	  {
		  try 
		  {
			 
			  clientSendReceive.onReceiveTxt(recvAllTxt) ; 			
		  }
		catch( Exception ex)
		  {
			logger.severe("Exception  onMessage");
			logger.throwing(ClientEndPtAny.class.getName(), "onMessage", ex);
			ex.printStackTrace();
			
			
		  }
	  }
	  @OnOpen
	  public void onOpen(Session session) {
	   
	    setCliSession(session);
	    System.out.println ("New Opening ClientEndPtAny : " +  ", session:" + session.getId() +"/" + serverURL );
		logger.info("New Opening ClientEndPtAny : " +  ", session:" + session.getId()+"/" + serverURL );
	    
	  }

	  @OnClose
	  public void  onClose(Session session, CloseReason cReason)
	  {
		  System.out.println ("Client closed: " +  ", session:" + session.getId() + ", CloseReason:" +cReason);
		  logger.info("Client closed: " +  ", session:" + session.getId() + ", CloseReason:" +cReason);
	  }
	  @OnError 
	  public void  error(Throwable t, Session session)
	    {
		  System.out.println ("Server closed: " +  ", session:" + session.getId() + ", Throwable:" +t.getMessage());
		 logger.severe("Exception  error");
		logger.throwing(ClientEndPtAny.class.getName(), "error", t);
		  t.printStackTrace();
		  
	  }
	 	
	public String getCliEndPtName() {
		return cliEndPtName;
	}

	public void setCliEndPtName(String cliEndPtName) {
		this.cliEndPtName = cliEndPtName;
	}
	public String getServerURL() {
		return serverURL;
	}
	public void setServerURL(String serverURL) {
		this.serverURL = serverURL;
	}
	public ClientSendReceive getClientSendReceive() {
		return clientSendReceive;
	}
	public void setClientSendReceive(ClientSendReceive clientSendReceive) {
		this.clientSendReceive = clientSendReceive;
	}
	public Session getCliSession() {
		return cliSession;
	}
	public void setCliSession(Session cliSession) {
		this.cliSession = cliSession;
	}

}
