package com.cloudmore.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

import static com.cloudmore.utility.Constants.USER_DIR;

public class ReportFormatter {
    private ReportFormatter(){}

    private static ExtentReports extent;
    private static final String REPORT_FILE_NAME = "Cloudmore-automation-report.html";
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String REPORT_FILE_PATH = System.getProperty(USER_DIR) + FILE_SEPARATOR + "AutomationReport";
    private static final String REPORT_FILE_LOCATION = REPORT_FILE_PATH + FILE_SEPARATOR + REPORT_FILE_NAME;

    public static void createInstance(){
        String fileName = getReportPath();
        extent = new ExtentReports();
        extent.attachReporter(setReporterConfig(fileName));
        setReporterEnvironmentDetails(extent);
    }

    private static void setReporterEnvironmentDetails(ExtentReports extent) {
        extent.setSystemInfo("Author", "Amala R");
    }

    private static String getReportPath() {
        File testDirectory = new File(ReportFormatter.REPORT_FILE_PATH);
        if(!testDirectory.exists()){
            if(testDirectory.mkdir()){
                return REPORT_FILE_LOCATION;
            }else{
                return System.getProperty(USER_DIR);
            }
        }
        return REPORT_FILE_LOCATION;
    }

    private static String setStyle(){
        return "#topbar { background-color: #8bb1ec; }" +
                ".topbar-items-right span { color: white; }" +
                ".menu span { color: darkgreen; }" +
                ".menu-item-selected span { border-bottom: 1px solid green; }"
                + "#dashboard { background-color: transparent; }" +
                ".test { border: 1px solid lightseagreen; }" +
                ".description { background-color: transparent; border-left: 2px solid orange; padding: 2px 15px;}" +
                ".name { color: darkgreen; }" +
                ".extent-table { border: 1px solid #bbb; }" +
                ".extent-table th { background: none repeat scroll 0 0 olivedrab; color: #fff; }" +
                ".extent-table td { border-bottom: 1px solid #bbb; }";
    }

    private static ExtentHtmlReporter setReporterConfig(String fileName){
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setTheme(Theme.DARK);
        htmlReporter.config().setDocumentTitle(REPORT_FILE_NAME);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setReportName(REPORT_FILE_NAME);
        htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
        htmlReporter.config().setCSS(setStyle());
        return htmlReporter;
    }
    public static ExtentReports getInstance() {
        if (extent == null)
            createInstance();
        return extent;
    }
}