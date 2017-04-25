package com.experitest.playground;

import com.experitest.appium.SeeTestAndroidDriver;
import com.experitest.appium.SeeTestCapabilityType;
import com.experitest.manager.client.PManager;
import com.experitest.manager.testng.ManagerITestListener;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * DevOpsChallenge
 * Created by tom.ben-simhon on 4/25/2017.
 */
@Listeners(ManagerITestListener.class)
public class Sandbox {


    protected DesiredCapabilities dc = new DesiredCapabilities();
    protected Properties cloudProperties = new Properties();
    protected String deviceQuery;
    SeeTestAndroidDriver driver;
    @BeforeTest
    public void setUpTest(ITestContext context) {
        deviceQuery = (context.getCurrentXmlTest().getParameter("deviceQuery") != null) ?
                context.getCurrentXmlTest().getParameter("deviceQuery") : null;
    }

    @BeforeMethod
    public void setUpMethod() throws Exception {
        initCloudProperties();
        File reporterDir = new File(System.getProperty("user.dir"), "reports");
        reporterDir.mkdirs();
        dc.setCapability(SeeTestCapabilityType.REPORT_DIRECTORY, reporterDir.getAbsolutePath());
        dc.setCapability(SeeTestCapabilityType.TEST_NAME,"testname");
        dc.setCapability(SeeTestCapabilityType.REPORT_FORMAT, "xml");
        dc.setCapability(SeeTestCapabilityType.USE_REMOTE_GRID, true);
        dc.setCapability(SeeTestCapabilityType.USERNAME, getProperty("griduser", cloudProperties));
        dc.setCapability(SeeTestCapabilityType.PASSWORD, getProperty("gridpass", cloudProperties));
        // In case your user is assign to a single project leave empty, otherwise please specify the project name
        dc.setCapability(SeeTestCapabilityType.PROJECT_NAME, getProperty("project", cloudProperties));

        // Set up the manager build ID and URL
        driver = new SeeTestAndroidDriver(new URL("http://localhost:8090"), dc);
    }

    @Test
    public void testReporter() {
        PManager.getInstance().addProperty("test","testing");

    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }


    protected String getProperty(String property, Properties props) throws FileNotFoundException, IOException {
        if (System.getProperty(property) != null) {
            return System.getProperty(property);
        } else if (System.getenv().containsKey(property)) {
            return System.getenv(property);
        } else if (props != null) {
            return props.getProperty(property);
        }
        return null;
    }

    private void initCloudProperties() throws FileNotFoundException, IOException {
        FileReader fr = new FileReader("cloud.properties");
        cloudProperties.load(fr);
        fr.close();
    }

}


