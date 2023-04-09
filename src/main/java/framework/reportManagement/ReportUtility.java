package framework.reportManagement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.currentThread;

public class ReportUtility {
    private ReportUtility(){}
    static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();
    static ExtentReports extent = ReportFormatter.getInstance();

    public static synchronized ExtentTest getTest(){
        return extentTestMap.get((int) (currentThread().threadId()));
    }

    public static synchronized void endTest(){
        extent.flush();
    }

    public static synchronized void startTest(String testName){
        ExtentTest test = extent.createTest(testName);
        extentTestMap.put((int) (currentThread().threadId()), test);
    }
}