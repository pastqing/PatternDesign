package com.su.startegy;

import com.su.annotation.XMLType;


public class Startegys {
	
	

}
@XMLType("Default")
class DefaultHead implements IProduct {
	public String generateXML() {
		return "Defalut XML";
	}
}
@XMLType("ChinaMobile")
class ChinaMobile implements IProduct{
	
	public String generateXML() {
		return "China Mobile XML";
	}
}

class ChinaUnicom implements IProduct {
	
	public String generateXML() {
		return "Chinal Unicom XML";
	}
}

class ChinaTele implements IProduct {
	
	public String generateXML() {
		return "China Tele XML";
	}
}

