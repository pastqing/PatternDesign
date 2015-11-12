package com.su.designPatterns;

import java.lang.reflect.Field;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Main {
	public static ArrayList<String> list = new ArrayList<String>();
	public static CopyOnWriteArrayList<String> clist = new CopyOnWriteArrayList<String>();

	public static void main(String[] args) throws NoSuchFieldException,
			SecurityException, IllegalArgumentException, IllegalAccessException {
		// System.out.println(ClassLoader.getSystemClassLoader());
		// System.out.println(5 >> 1);
		clist.add("su");
		clist.add("qing");
		new Thread() {
			public void run() {
				// Main.this.list.add("1");
				// Main.this.list.add("123");
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("add thread start");
				clist.add("a");
				try {
					TimeUnit.MILLISECONDS.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				clist.add("b");
				// TimeUnit.MILLISECONDS.sleep(500);
				// Main.this.list.add("456");
				clist.add("c");
				System.out.println("last finish");
				System.out.println("Length: " + clist.size());

			}
		}.start();

		new Thread() {
			public void run() {
				// Iterator<String> itr = Main.this.list.iterator();
				Iterator<String> citr = clist.iterator();

				while (true) {
					while (citr.hasNext()) {
						System.out.println("CopyOnwriteArrayList:  "
								+ citr.next());
					}
					if(clist.size() == 3) 
						break;
				}
			}
		}.start();
		// while(itr.hasNext()) {
		// System.out.println(itr.next());
		// }
		// list.remove(null);
		// System.out.println(list.size());
		//
		// Field f1 = list.getClass().getDeclaredField("elementData");
		// f1.setAccessible(true);
		// Object[] objs = (Object[]) f1.get(list);
		// System.out.println(objs.length);

	}
}
