package com.company.prototype.util;

import java.util.concurrent.LinkedBlockingQueue;

import com.company.prototype.model.entity.Log;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
/*
 * Un simple methodo de logging asynchrono, 
 * UNa mejor implementación sería utilizar una cola de JMS
 * 
 * */
@Singleton
@Startup
public class SimpleDBLogging{
	
	//private static SimpleDBLogging singleton= null;
	private Thread t;
	
	/*private SimpleDBLogging(){
		
	}*/
	
    @PersistenceContext(unitName = "prototypeUnit")
    private EntityManager em;
    
    private LinkedBlockingQueue<Log> queue = new LinkedBlockingQueue<Log>();
    private volatile boolean terminate = false;

    private EntityManager getEntityManager() {
        return em;
    }
	
	@PostConstruct
	private void startup(){
		/*t=new Thread(new Runnable(){

			@Override
			 public void run() {
		         while(!terminate) {
		            Log log;
					try {
						log = queue.take();
						//getEntityManager().joinTransaction();
						
						getEntityManager().persist(log);
						
					} catch (InterruptedException e) {
						e.printStackTrace();       
					}  catch (Exception t) {
						t.printStackTrace();
					}   
		         }
		     }
			
		});
		t.start();*/
		System.out.println("test 5555");
	}
	
	/*public static SimpleDBLogging getInstace(){
		
		if(singleton==null){
			singleton= new SimpleDBLogging();
			t=new Thread(singleton);	
			t.start();
		}
		//verificar cada 100 veces
		if (x>100 && !t.isAlive()){
			t.start();
			x=0;
		}		
		x++;
		return singleton;
	}*/

	@Asynchronous
     public void addLog(Log log) {
		getEntityManager().persist(log);
    	 

         //queue.add(data);
     }
 

}
