package com.ford.gux.tests.selenium.Utils;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileInputStream;


public class ReadXMLFile  {

    public static NodeList readXML(String fileName){
        NodeList nList = null;
        File fXmlFile = new File(fileName );
    try {
        FileInputStream in = new FileInputStream(fXmlFile);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(in,"UTF-8");

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();

        nList = doc.getElementsByTagName("Dealer");

    } catch (Exception e) {
        e.printStackTrace();
    }
        return nList;
   }

}
