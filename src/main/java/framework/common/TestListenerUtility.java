package framework.common;

import com.aventstack.extentreports.Status;
import framework.reportManagement.ReportUtility;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.logging.Logger;

public class TestListenerUtility implements ITestListener {

    private static String getTestMethodName(ITestResult iTestResult) {
        return iTestResult.getMethod().getConstructorOrMethod().getName();
    }

    @Override
    public void onStart(ITestContext iTestContext) {
        Logger.getLogger("onStart method " + iTestContext.getName());
    }

    @Override
    public void onFinish(ITestContext iTestContext) {
        Logger.getLogger("onFinish method " + iTestContext.getName());
        ReportUtility.endTest();
    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        Logger.getLogger(getTestMethodName(iTestResult) + " test is starting.");
        ReportUtility.startTest(getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        Logger.getLogger(getTestMethodName(iTestResult) + " test succeeded.");
        ReportUtility.getTest().log(Status.PASS, "Test Method Name Passed: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        Logger.getLogger(getTestMethodName(iTestResult) + " test failed.");
        ReportUtility.getTest().log(Status.FAIL, "Test Method Name Failed: " + getTestMethodName(iTestResult));
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        Logger.getLogger(getTestMethodName(iTestResult) + " test got skipped.");
        ReportUtility.getTest().log(Status.SKIP, "Test Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
        Logger.getLogger("Test failed but it is in defined success ratio " + getTestMethodName(iTestResult));
    }

}