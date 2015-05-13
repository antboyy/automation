package com.ford.gux.tests.selenium;

public enum LADMarket {

	FRENCH("FRANCE", "http://m.intpublish-fr.engine.ford.com/GUXDealerLocator"),
	GERMAN("GERMANY", "http://m.intpublish-de.engine.ford.com/GlobalUXDealerLocator"),
	DUTCH("NETHERLANDS", "http://intpublish-nl.engine.ford.com/DealerLocatorGUX"),
	ITALIAN("ITALY", "http://intpublish-it.engine.ford.com/DealerLocatorGux"),
	SPANISH("SPAIN", "http://intpublish-es.engine.ford.com/OwnerServicesMike/DealerLocatorGux"),
	GREEK("GREECE", "http://intpublish-gr.engine.ford.com/DealerLocatorGux"),
	CZECH("CZECH REPUBLIC", "http://intpublish-cz.engine.ford.com/Footer/DealerLocatorGux"),
	AUSTRIAN("AUSTRIA", "http://intpublish-at.engine.ford.com/OwnerServices/DealerLocatorGux"),
	UK("UNITED KINGDOM", "http://m.ford.co.uk/DealerLocator"),
	PORTUGAL("PORTUGAL", "http://intpublish-pt.engine.ford.com/DealerLocatorGux"),
	HUNGARIAN("HUNGARY", "http://intpublish-hu.engine.ford.com/DealerLocatorGux");
	
	private String marketName;
	private String marketURL;
	
	LADMarket(String marketName, String marketURL){
		this.marketName = marketName;
		this.marketURL = marketURL;
	}
	
	public String getMarketURL(){
		return marketURL;
	}
	
	public String getMarketName(){
		return marketName;
	}
		
}
