import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.SQLOutput;

public class ExtentReportDemo {

    ExtentReports extent; //we have declare this variable here to have possibiliy to use it in our initialDemo test

    @BeforeTest
    public void config() {
//        ExtentSparkReporter expects path where your report should be created
        String destinationPathForReports = System.getProperty("user.dir") + "\\reports\\index.html";
        ExtentSparkReporter reporter = new ExtentSparkReporter(destinationPathForReports); //this is a class which help us to configure how we would like our report to look like
        reporter.config().setReportName("Web Automation Results (.config().setReportName)");
        reporter.config().setDocumentTitle("Test Results (.config().setDocumentTitle)");

        extent = new ExtentReports(); //this is our main class, it will drive all our report execution
        //with ExtentSparkReporter we created our report and now we have to attach it to our main class
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester", "Iza (.setSystemInfo)");
    }

    @Test
    public void initialDemo() {
        ExtentTest test = extent.createTest("Initial Demo (.createTest)"); //we are creating object for our test here because we can use after methods for it for example - taking a screenshot .addScreenCaptureFromBase64String()
        System.getProperty("Webdriver.chrome.driver", "C:\\Users\\IzabelaMilisiewicz\\Downloads\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/");
        System.out.println(driver.getTitle());
        driver.close();
        test.fail("Result do not match (forced fail with method .fail"); //it will show filed test in the report but in intellij you will see passed
        //in next lectures it will be shown how to connect report with testng listeners thanks to which we will know when test filed
        extent.flush(); //it will stop monitoring test, it's mandatory
    }
}