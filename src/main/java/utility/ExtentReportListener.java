package utility;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentReportListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        System.setProperty("extent.reporter.spark.out",
            "target/ExtentReport/TestAutomationReport_" + timestamp + ".html");
    }

    @Override
    public void onFinish(ISuite suite) {
        // nothing needed here
    }
    
}
