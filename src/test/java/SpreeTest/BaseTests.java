package SpreeTest;

import Listener.CustomListner;
import Utils.ReportUtils;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITest;
import org.testng.ITestListener;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.google.common.io.Files.copy;
import static org.apache.commons.io.FileUtils.*;

@Listeners(CustomListner.class)
public class BaseTests implements ITestListener {

    public static WebDriver driver;

   /*String reportFileName;
   ReportUtils reportUtils;
    String currentWorkingDirectory;*/

  /* @BeforeSuite
   public void preSetup() throws Exception{
       currentWorkingDirectory = System.getProperty("user.dir");
         reportFileName = currentWorkingDirectory+"/Users/sonalmishra/IdeaProjects/SpreeProjectPOMModel/src/test/java/Reports/SpreeReport.html";
         reportUtils = new ReportUtils(reportFileName);
   }*/

    @BeforeMethod
    public void setUp()
    {

        System.out.println("driverPath="+System.getProperty("user.dir")+"/Users/sonalmishra/IdeaProjects/SpreeProjectPOMModel/src/drivers/chromedriver");
        System.setProperty("webdriver.chrome.driver","/Users/sonalmishra/IdeaProjects/SpreeProjectPOMModel/src/drivers/chromedriver");
        driver=new ChromeDriver();
       // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();


    }

   @AfterMethod
   public void tearDown() {
       driver.quit();
   }

   //Taking Screenshots
           public void takeSnapShot( String testName) throws IOException {
               //Date d = new Date();
               //TakesScreenshot scrShot = ((TakesScreenshot)driver);
               try {
                   File scrFile = ((TakesScreenshot) driver)
                           .getScreenshotAs(OutputType.FILE);
                   File destFile = new File("/Users/sonalmishra/IdeaProjects/SpreeProjectPOMModel/src/test/java/Screenshots/" + testName +".png");
                   Files.copy(scrFile, destFile);
               }
               catch (IOException e){
                   e.printStackTrace();
               }
       }}

       /*@AfterSuite
    public void postTearDown()
       {
           reportUtils.flushReport();
       }
   }*/


