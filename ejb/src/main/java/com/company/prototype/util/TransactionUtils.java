package com.company.prototype.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class TransactionUtils {
	
	private static final AtomicInteger counter = new AtomicInteger(100000);
	private static final String PREFIX = "";	


	
	public static String generateUniqueID() {  
		  UUID uniqueKey = UUID.randomUUID();  
		  
		  return uniqueKey.toString();  
	}  
	




	public static String generateIntegerID() {
	    final int number = counter.incrementAndGet();
	    return PREFIX + number;
	}
	
	
	
	

		
	public static void main(String[] args) {
		for(int i=0;i<15;i++){
			Thread t=new Thread(){
				
				public void run(){
					for(int i=0;i<25;i++){
						System.out.println(generateIntegerID());
					}							
						
				}
				
			};
			t.run();			
		}		
	}

}
