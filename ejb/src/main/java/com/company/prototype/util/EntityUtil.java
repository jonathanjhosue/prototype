package com.company.prototype.util;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class EntityUtil {
	
	private static final AtomicInteger counter1 = new AtomicInteger(1000);


private static final AtomicInteger counter = new AtomicInteger(100000);
private static final String PREFIX = "";	

	
	private static AtomicReference<Long> currentTime = 
	               new AtomicReference<>(System.currentTimeMillis());
	 
	public static Long nextId() {
        Long prev; 
        Long next = System.currentTimeMillis();
        do {
            prev = currentTime.get();
            next = next > prev ? next : prev + 1;        
        } while (!currentTime.compareAndSet(prev, next));        
        return next;
    }
	
	
public static String generateUniqueID() {  
	  UUID uniqueKey = UUID.randomUUID();  
	  
	  return uniqueKey.toString();  
}  


public static String generateIntegerID() {
    final int number = counter1.incrementAndGet();
    return PREFIX + number;
}


}
