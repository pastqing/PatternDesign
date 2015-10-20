package com.su.startegy;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.su.annotation.XMLType;

public class ProductFactory {

	// singleton
	private ProductFactory() {

	}

	public static ProductFactory getInstance() {
		return ProductFactoryInstance.instance;
	}

	private static class ProductFactoryInstance {
		static final ProductFactory instance = new ProductFactory();
	}

	public IProduct createProduct(String productType) throws URISyntaxException {

		// load all startegys
		List<Class<? extends IProduct>> startegyList = loadAllStrategy("com.su.startegy");
		//
		for(Class<? extends IProduct> clazz : startegyList) {
			XMLType xmlType = praseAnnotation(clazz);
			if(xmlType.value().equals(productType))
				try {
					return clazz.newInstance();
					
				} catch (InstantiationException | IllegalAccessException e) {
					e.printStackTrace();
				}
		}
		
		//create IProduct Object failed
		return null;
	}

	private List<Class<? extends IProduct>> loadAllStrategy(String packageName) throws URISyntaxException {
		List<Class<? extends IProduct>> strategyList = new ArrayList<Class<? extends IProduct>>();
		URI filePath = getClass().getClassLoader().getResource(packageName.replace(".", "/")).toURI();
		System.out.println(filePath);
		File[] files = new File(filePath).listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if (name.endsWith(".class"))
					return true;
				return false;
			}
		});
		// load class
		
		for (File file : files) {
			try {
				Class clazz = getClass().getClassLoader().loadClass(
						packageName + "." + file.getName().replace(".class", ""));
				if (clazz != IProduct.class
						&& IProduct.class.isAssignableFrom(clazz)) {
					strategyList.add(clazz);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return strategyList;
	}

	private XMLType praseAnnotation(Class<? extends IProduct> clazz) {

		XMLType xmlType = clazz.getAnnotation(XMLType.class);
		if (xmlType == null) {
			return null;
		}
		return xmlType;
	}

	public static void main(String[] args) throws URISyntaxException,
			ClassNotFoundException {
		IProduct product = ProductFactory.getInstance().createProduct("ChinaMobile");
		System.out.println(product.getClass().getName());
	}

}
