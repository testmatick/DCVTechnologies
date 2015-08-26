package com.core.base;

import com.core.reporting.Reporter;
import com.core.utils.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.Set;

public class BaseActions {
    protected static WebDriver driver;

    public BaseActions() {
        BaseActions.driver = BaseTest.driver;
    }

    public void closeTracker(){
        try{
            BaseTest.driver.quit();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void waitForTabsCount(int minTabsCount) {
        Reporter.log("Waiting for >= " + minTabsCount + " tabs opened (up to " + Constants.ELEMENT_EXTRALONG_TIMEOUT_SECONDS + " seconds)..");
        long timeBefore = System.currentTimeMillis();
        do {
            wait(Constants.ELEMENT_MICRO_TIMEOUT_SECONDS);
        }
        while (driver.getWindowHandles().size() < minTabsCount && System.currentTimeMillis() - timeBefore <= Constants.ELEMENT_EXTRALONG_TIMEOUT_SECONDS * 1000);

        Assert.assertTrue(driver.getWindowHandles().size() >= minTabsCount, "Number of opened tabs is not more or equals to " + minTabsCount);
    }

    public void closeOtherTabs() {
        Reporter.log("Closing other browser tabs");
        String currentHandle = driver.getWindowHandle();
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(currentHandle)) {
                driver.switchTo().window(handle);
                driver.close();
            }
        }
        driver.switchTo().window(currentHandle);
    }


    public void wait(int waitInSeconds) {
        try {
            Thread.sleep(waitInSeconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}
