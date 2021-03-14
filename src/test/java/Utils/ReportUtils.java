package Utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.testng.Reporter;

public class ReportUtils {

    ExtentHtmlReporter htmlReport;
    ExtentReports extentReports;
    ExtentTest extentTest;

    public ReportUtils(String htmlReportFilename)
    {
        htmlReportFilename = htmlReportFilename.trim();

        htmlReport = new ExtentHtmlReporter(htmlReportFilename);

        extentReports = new ExtentReports();

        extentReports.attachReporter(htmlReport);
    }

    public void createATestCase(String testCaseName){
        extentTest = extentReports.createTest(testCaseName);
    }

    public void addTestLog(Status status ,String comment){
        extentTest.log(status,comment);
    }

    public void flushReport()
    {
        extentReports.flush();
    }
}
