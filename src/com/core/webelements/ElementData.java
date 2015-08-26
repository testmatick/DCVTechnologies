package com.core.webelements;

import com.core.reporting.Reporter;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.w3c.dom.Element;

import java.util.MissingFormatArgumentException;

/**
 * @author William
 *         Date: 25.07.13
 */
public class ElementData {
    private String name;
    private LocatorTypes type = null;
    private String value = "";

    public ElementData(Element xmlElement) {
        name = xmlElement.getAttribute("name");

        if (type == null) {
            value = LocatorsParser.getTextValue(xmlElement, "xpath");
            type = (value.length() > 0) ? LocatorTypes.XPATH : null;
        }

        if (type == null) {
            value = LocatorsParser.getTextValue(xmlElement, "css");
            type = (value.length() > 0) ? LocatorTypes.CSS : null;
        }

        if (type == null) {
            value = LocatorsParser.getTextValue(xmlElement, "id");
            type = (value.length() > 0) ? LocatorTypes.ID : null;
        }

        if (type == null) {
            value = LocatorsParser.getTextValue(xmlElement, "name");
            type = (value.length() > 0) ? LocatorTypes.NAME : null;
        }

        if (type == null) {
            value = LocatorsParser.getTextValue(xmlElement, "tag");
            type = (value.length() > 0) ? LocatorTypes.TAG : null;
        }

        if (type == null) {
            value = LocatorsParser.getTextValue(xmlElement, "linkText");
            type = (value.length() > 0) ? LocatorTypes.LINK_TEXT : null;
        }

        if (type == null) {
            value = LocatorsParser.getTextValue(xmlElement, "partialLinkText");
            type = (value.length() > 0) ? LocatorTypes.PARTIAL_LINK_TEXT : null;
        }
    }

    public LocatorTypes getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    public String getLocatorValue(Object... args) {
        return String.format(value, args);
    }

    public By returnLocator(Object... args) {
        By by = null;

        try {

            String locator = String.format(value, args);

            if (by == null) {
                by = (type == LocatorTypes.XPATH) ? By.xpath(locator) : null;
            }
            if (by == null) {
                by = (type == LocatorTypes.CSS) ? By.cssSelector(locator) : null;
            }
            if (by == null) {
                by = (type == LocatorTypes.ID) ? By.id(locator) : null;
            }
            if (by == null) {
                by = (type == LocatorTypes.NAME) ? By.name(locator) : null;
            }
            if (by == null) {
                by = (type == LocatorTypes.TAG) ? By.tagName(locator) : null;
            }
            if (by == null) {
                by = (type == LocatorTypes.LINK_TEXT) ? By.linkText(locator) : null;
            }
            if (by == null) {
                by = (type == LocatorTypes.PARTIAL_LINK_TEXT) ? By.partialLinkText(locator) : null;
            }

        } catch (MissingFormatArgumentException e) {
            Reporter.log("Wrong locator arguments for element '" + name + "'");
            throw new MissingFormatArgumentException(e.getFormatSpecifier());
        }

        Assert.assertNotNull(by, String.format("Locator for element '%s' is not found in UIXML config file!", name));

        return by;
    }

}
