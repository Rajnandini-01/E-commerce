package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

private static ExtentReports extent;

public static ExtentReports getInstance() {
    if (extent == null) {

        // Report path
        String reportPath = System.getProperty("user.dir") + "/test-output/ExtentReport.html";

        ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
        spark.config().setReportName("BSTACK Demo Automation Report");
        spark.config().setDocumentTitle("Test Execution Report");

        extent = new ExtentReports();
        extent.attachReporter(spark);

        extent.setSystemInfo("Project", "BSTACK Demo");
        extent.setSystemInfo("Tester", "Juhi");
        extent.setSystemInfo("Environment", "QA");
    }
    return extent;
}

}
