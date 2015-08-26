package com.core.reporting;

import br.eti.kinoshita.testlinkjavaapi.constants.ExecutionStatus;
import com.core.utils.Constants;
import com.core.base.BaseTest;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;


public class TestListener extends TestListenerAdapter implements IInvokedMethodListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String imageName = result.getName() + "-" + System.currentTimeMillis() + ".png";
        org.testng.Reporter.setCurrentTestResult(result);
//        Reporter.log("Screenshot saved:<br></br><a href = '" + imageName + "'><img src = '" + imageName + "' width='600'/></a>");
//
//        try {
//            File scrFile = ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.FILE);
//            FileUtils.copyFile(scrFile, new File(System.getProperty("report.dir") + File.separatorChar + "html" + File.separatorChar + imageName));
//        } catch (UnreachableBrowserException ubs) {
//            killFirefoxOnHang();
//        } catch (IOException e) {
//            Reporter.log("Error saving screenshot!");
//        }

        // increase fails counter
        try {
            File counterFile = new File(System.getProperty("report.dir") + File.separator + "fails");

            BufferedReader bufferedReader = new BufferedReader(new FileReader(counterFile));
            String str = bufferedReader.readLine();
            int counter = 0;
            if (str != null) {
                counter = Integer.parseInt(str);
            }
            counter++;
            bufferedReader.close();

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(counterFile, false));
            bufferedWriter.write(String.valueOf(counter));
            bufferedWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        this.savePageSource(result);
    }

    private void killFirefoxOnHang() {
        Reporter.log("Oh, man. My browser crashed! I'll kill last scraps of firefox.");
        if (System.getProperty("platform", "React OS").equalsIgnoreCase("WINDOWS") &&
                System.getProperty("browser", "Konqueror").equalsIgnoreCase("firefox")) {
            try {
                Runtime.getRuntime().exec(new String[]{"taskkill", "/im:firefox.exe", "/f"});
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void savePageSource(ITestResult result) {
        String source;
        try {
            source = BaseTest.driver.getPageSource();
        } catch (Exception e) {
            Reporter.log("Can not save page source!");
            System.out.println("Can not save page source!");
            e.printStackTrace();
            return;
        }
        String dirName = System.getProperty("report.dir") + File.separator + "page_sources";
        File dir = new File(dirName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String testClass = result.getTestClass().getName();
        int index = testClass.lastIndexOf('.' + 1);
        index = index == -1 ? 0 : index;
        testClass = testClass.substring(index);
        File toSave = new File(dirName + File.separator + testClass + "." + result.getName() + ".html");
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(toSave, false));
            bufferedWriter.write(String.valueOf(source));
            bufferedWriter.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void beforeInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
    }

    @Override
    public void afterInvocation(IInvokedMethod iInvokedMethod, ITestResult iTestResult) {
        Method method = iInvokedMethod.getTestMethod().getConstructorOrMethod().getMethod();
        for (Annotation a : method.getDeclaredAnnotations()) {
            if (a instanceof TestLinkTest && !Constants.TESTLINK_ENABLED) {
                TestLinkTest annotation = (TestLinkTest) a;
                int testPlanId = annotation.testPlanId();
                int testCaseId = annotation.testCaseId();
                int testResult = iTestResult.getStatus();
                ExecutionStatus executionStatus;
                switch (testResult) {
                    case (ITestResult.SUCCESS):
                        executionStatus = ExecutionStatus.PASSED;
                        break;
                    case (ITestResult.FAILURE):
                        executionStatus = ExecutionStatus.FAILED;
                        break;
                    case (ITestResult.SKIP):
                        executionStatus = ExecutionStatus.BLOCKED;
                        break;
                    default:
                        executionStatus = ExecutionStatus.NOT_RUN;
                        break;
                }
                TestLinkAPI.setStatus(testCaseId, testPlanId, executionStatus);
            }
            if (a instanceof IE && System.getProperty("browser").equals("ie")) {
                iTestResult.setStatus(ITestResult.SKIP);
            }
        }
    }
}
