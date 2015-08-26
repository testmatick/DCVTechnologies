package com.core.base;

import com.autotests.control.Actions;
import com.autotests.control.Pages;
import com.core.reporting.Reporter;
import com.core.utils.Constants;
import com.core.utils.Formatter;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class BaseTest {

    public static WebDriver driver;

    protected void startFirefox() throws IOException {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void setupFirefoxRemoteDriver(String hubUrl, String platformName) throws IOException {
        Platform platform = (platformName != null) ? Platform.valueOf(platformName) : Platform.ANY;

        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        capabilities.setBrowserName("firefox");
        capabilities.setPlatform(platform);
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        driver = new CustomRemoteWebDriver(new URL(hubUrl), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void startFirefox(String profileLocation) {
        FirefoxProfile ffProfile = new FirefoxProfile(new File(profileLocation));
        driver = new FirefoxDriver(ffProfile);
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void startInternetExplorer() throws IOException {
        String pathToDriver = System.getProperties().getProperty("browsers.dir") + File.separatorChar + "IEDriverServer_32.exe";
        System.setProperty("webdriver.ie.driver", pathToDriver);
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setCapability("nativeEvents", false);
        driver = new InternetExplorerDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void setupIERemoteDriver(String hubUrl, String platformName) throws IOException {
        Platform platform = (platformName != null) ? Platform.valueOf(platformName) : Platform.ANY;

        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setBrowserName("internet explorer");
        capabilities.setPlatform(platform);
        capabilities.setCapability("nativeEvents", false);
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        driver = new CustomRemoteWebDriver(new URL(hubUrl), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void startChrome() throws IOException {
        String platform = System.getProperty("platform");

        String driversFolder = System.getProperties().getProperty("browsers.dir") + File.separatorChar;
        String pathToDriver = (platform.equals("WINDOWS")) ? driversFolder + "chromedriver.exe" : driversFolder + "chromedriver";

        System.setProperty("webdriver.chrome.driver", pathToDriver);
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        driver = new ChromeDriver(capabilities) {
            @Override
            public WebElement findElement(By by) {
                try {
                    return by.findElement(this);
                } catch (org.openqa.selenium.NoSuchElementException nse) {
                    Field f = null;
                    try {
                        f = Throwable.class.getDeclaredField("detailMessage");
                    } catch (NoSuchFieldException e) {
                        throw nse;
                    }
                    f.setAccessible(true);
                    try {
                        String error = "\n" + by.toString() + "\n" + f.get(nse);
                        f.set(nse, error);
                    } catch (IllegalAccessException ia) {
                    }
                    throw nse;
                }
            }
        };
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void setupChromeRemoteDriver(String hubUrl, String platformName) throws IOException {
        Platform platform = (platformName != null) ? Platform.valueOf(platformName) : Platform.ANY;

        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setBrowserName("chrome");
        capabilities.setPlatform(platform);
        capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);

        driver = new CustomRemoteWebDriver(new URL(hubUrl), capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Constants.ELEMENT_TIMEOUT_SECONDS, TimeUnit.SECONDS);
    }

    protected void closeBrowser() {
        driver.quit();
    }

    /**
     * An function that fetches the table from the excel sheet and returns it as
     * an array of array of String type. It uses Java Excel API to fetch data
     * from excel sheet.
     *
     * @param xlFilePath the path of XL file/workbook containing the data, the path is
     *                   relative to java core
     * @param sheetName  name of the xls sheet that contains the table
     * @param tableName  Name of the table that you wish to fetch
     */
    public static String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception {

        Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
        String browser = System.getProperty("browser", "firefox").toLowerCase();
        if (browser.equals("ie"))
            browser = "chrome";                               //hack to execute tests on ie without datapool creation
        Sheet sheet = workbook.getSheet(sheetName);
        int startRow, startCol, endRow, endCol, ci, cj;
        Cell tableStart = sheet.findCell(tableName);
        startRow = tableStart.getRow();
        startCol = tableStart.getColumn();

        Cell tableEnd = sheet.findCell(tableName, startCol + 1, startRow + 1, 100, 64000, false);

        endRow = tableEnd.getRow();
        endCol = tableEnd.getColumn();

        String[][] tabArray = new String[endRow - startRow - 1][endCol - startCol - 1];
        ci = 0;

        for (int i = startRow + 1; i < endRow; i++, ci++) {
            cj = 0;
            for (int j = startCol + 1; j < endCol; j++, cj++) {
                tabArray[ci][cj] = sheet.getCell(j, i).getContents();
            }
        }
        return (tabArray);
    }


    @BeforeClass
    public void startBrowser() throws IOException {
        boolean makeAllScreenshots = Reporter.isMakeScreenShotOnLog();
        Reporter.makeScreenshotOnLog(false);
        String message = "* Starting test " + this.getClass().toString();
        Reporter.log("\n" + message);
        System.out.println(message);


        String hubUrl = System.getProperty("hub");
        String browser = (System.getProperty("browser") == null) ? "firefox" : System.getProperty("browser");
        String platform = System.getProperty("platform");

        if (browser.equalsIgnoreCase("ie")) {
            if (hubUrl != null) {
                this.setupIERemoteDriver(hubUrl, platform);
            } else {
                this.startInternetExplorer();
            }

        } else if (browser.equalsIgnoreCase("chrome")) {
            if (hubUrl != null) {
                this.setupChromeRemoteDriver(hubUrl, platform);
            } else {
                this.startChrome();
            }

        }else {
            if (hubUrl != null) {
                this.setupFirefoxRemoteDriver(hubUrl, platform);
            } else {
                this.startFirefox();
            }
        }

        String baseUrl = System.getProperty("baseurl");
        if (baseUrl != null) {
            Constants.BASE_URL = baseUrl;
        }

        Pages.setupPages();
        Actions.setupActions();
        Reporter.makeScreenshotOnLog(makeAllScreenshots);
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() {
        boolean makeAllScreenshots = Reporter.isMakeScreenShotOnLog();
        Reporter.makeScreenshotOnLog(false);
        Reporter.log(" * Stopping WebDriver");
        closeBrowser();
        Reporter.makeScreenshotOnLog(makeAllScreenshots);
    }

    protected void skipTest(String message) {
        throw new SkipException(Formatter.escapeCharacters(message));
    }

}
