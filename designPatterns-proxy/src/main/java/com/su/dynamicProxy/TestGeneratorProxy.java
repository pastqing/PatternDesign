package com.su.dynamicProxy;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.ProxyGenerator;

public class TestGeneratorProxy {
	public static void main(String[] args) throws IOException {

		byte[] classFile = ProxyGenerator.generateProxyClass("TestProxyGen",
				Subject.class.getInterfaces());
		File file = new File("/Users/yadoao/Desktop/TestProxyGen.class");
		FileOutputStream fos = new FileOutputStream(file);  
        fos.write(classFile);  
        fos.flush();  
        fos.close();  

	}
}
