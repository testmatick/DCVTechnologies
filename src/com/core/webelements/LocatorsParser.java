package com.core.webelements;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.HashMap;
import java.util.Map;

/**
 * @author William
 *         Date: 25.07.13
 */
public class LocatorsParser {

    public static Map<String, ElementData> getLocatorsFromXML(String fileName) {
        Map<String, ElementData> locators = new HashMap<String, ElementData>();
        Document dom = parseXmlFile(fileName);

        //get the root element
        Element rootElements = dom.getDocumentElement();

        //get a nodelist of elements
        NodeList nl = rootElements.getElementsByTagName("WebElement");

        if (nl != null && nl.getLength() > 0) {

            for (int nodeIndex = 0; nodeIndex < nl.getLength(); nodeIndex++) {

                //get the WebElement element
                Element element = (Element) nl.item(nodeIndex);

                String name = element.getAttribute("name");
                locators.put(name, new ElementData(element));
            }
        }

        return locators;
    }


    private static Document parseXmlFile(String fileName) {
        Document dom = null;

        //get the factory
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try {

            //Using factory get an instance of document builder
            DocumentBuilder db = dbf.newDocumentBuilder();

            System.out.println(db);
            //parse using builder to get DOM representation of the XML file
            dom = db.parse(fileName);


        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            return dom;
        }
    }

    public static String getTextValue(Element xmlElement, String tagName) {
        String textVal = "";

        NodeList nl = xmlElement.getElementsByTagName(tagName);

        if (nl != null && nl.getLength() > 0) {

            Element el = (Element) nl.item(0);
            textVal = el.getFirstChild().getNodeValue();
        }

        return textVal;
    }

}
