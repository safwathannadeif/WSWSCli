package com.shdp.websockets.webservice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class PoolThrd {
private final boolean wantExceptionOnReject = false;
public ThreadPoolExecutor  init2(int coreSize, int maxSize,int keepAliveTime)
{
	  //  pool Queue fixed queue 20
	  BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(20, true); 	//If true(FIFO) then queue accesses for threads blocked on
	  																				//insertion or removal, are processed in FIFO order
	  																				//If false the access order is unspecified.
	  ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			  coreSize, 		// core size
			  maxSize, 			// max size
			  keepAliveTime, 	// keep alive time for the
			  TimeUnit.MINUTES, // keep alive time units for core and extra threads 
			  queue 			// the FIFO	queue to use
	  );
	  threadPoolExecutor.allowCoreThreadTimeOut(false);  //core size live all the time,
	  										// When false, core threads are never terminated due to lack of incoming tasks
	  // set rejected execution handler
	  if (!wantExceptionOnReject) threadPoolExecutor.setRejectedExecutionHandler(new RejectedHandler());
	  return(threadPoolExecutor);
	 /* ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
			    2, // core size
			    3, // max size
			    1, // keep alive time for the
			    TimeUnit.MINUTES, // keep alive time units for core and extra threads 
			    queue 				// the FIFO	queue to use
			  );*/
}
private  class RejectedHandler implements RejectedExecutionHandler {
	  public void rejectedExecution(Runnable arg0, ThreadPoolExecutor arg1) {
	    // TODO Auto-generated method stub
	    System.err.println(Thread.currentThread().getName() + " execution rejected: " + arg0);     
	  }
	}
}
