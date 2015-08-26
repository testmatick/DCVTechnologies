package com.core.reporting;

import com.core.base.BaseTest;
import com.core.utils.Formatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.UnreachableBrowserException;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reporter {

    private static boolean active = true;
    private static boolean makeScreenShotOnLog = System.getProperty("makeScreenShotOnLog", "false").equalsIgnoreCase("true");

    public static void log(String s) {
        if (active) {
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("H:mm:ss:SSS");
            sdf.format(cal.getTime());
            org.testng.Reporter.log("[" + sdf.format(cal.getTime()) + "]: " + Formatter.escapeCharacters(s) + "<br></br>");
        }
        if (active && makeScreenShotOnLog) {
            String imageName = System.currentTimeMillis() + ".png";
            org.testng.Reporter.log("<a href = '" + imageName + "'><img src = '" + imageName + "' width='400'/></a><br></br>");
            try {
                File scrFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(scrFile, new File(System.getProperty("report.dir") + File.separatorChar + "html" + File.separatorChar + imageName));
            } catch (Exception e) {
                org.testng.Reporter.log("Error saving screenshot!<br></br>");
            }
        }
    }

    public static void setActive(boolean active) {
        Reporter.active = active;
    }

    public static boolean isMakeScreenShotOnLog() {
        return makeScreenShotOnLog;
    }

    public static void makeScreenshotOnLog(boolean makeScreenShotOnLog) {
        Reporter.makeScreenShotOnLog = makeScreenShotOnLog;
    }

}
