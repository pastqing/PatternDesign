package com.su.designPatterns;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestLazyNotSafe {
	
	boolean lock;
	
	public void setLock(boolean lock) {
		this.lock = lock;
	}
	public boolean getLock() {
		return lock;
	}
	
	public static void main(String[] args) throws InterruptedException {
		final Set<String> instanceSet = Collections.synchronizedSet(new HashSet<String>());
		
		final TestLazyNotSafe test = new TestLazyNotSafe();
		test.setLock(true);
		ExecutorService executorService = Executors.newCachedThreadPool();
		
		for(int i = 0; i < 20; i++) {
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					System.out.println("Thread: " + Thread.currentThread().getName());
					while(true) {
//						if(!test.getLock())	{
							System.out.println("Thread: " + Thread.currentThread().getName()
									+ " create a new instance");
							LazyNotSafe singleton = LazyNotSafe.getInstance();
							instanceSet.add(singleton.toString());
							break;
//						}
					}
				}
			});
		}
		Thread.sleep(500);
		test.setLock(false);
		Thread.sleep(500);
		
		for(String s : instanceSet) {
			System.out.println(s);
		}
		executorService.shutdown();  
	}
}
