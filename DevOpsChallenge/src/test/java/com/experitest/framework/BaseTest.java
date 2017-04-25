package com.experitest.framework;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import com.experitest.manager.testng.ManagerITestListener;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.experitest.appium.SeeTestCapabilityType;
import com.experitest.manager.client.PManager;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

@Listeners(ManagerITestListener.class)
public class BaseTest {

    protected DesiredCapabilities dc = new DesiredCapabilities();
    protected Properties cloudProperties = new Properties();
    protected String deviceQuery;

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
        dc.setCapability(SeeTestCapabilityType.REPORT_FORMAT, "xml");
        dc.setCapability(SeeTestCapabilityType.USE_REMOTE_GRID, true);
        dc.setCapability(SeeTestCapabilityType.USERNAME, getProperty("griduser", cloudProperties));
        dc.setCapability(SeeTestCapabilityType.PASSWORD, getProperty("gridpass", cloudProperties));
        // In case your user is assign to a single project leave empty, otherwise please specify the project name
        dc.setCapability(SeeTestCapabilityType.PROJECT_NAME, getProperty("project", cloudProperties));

        // Set up the manager build ID and URL
        System.setProperty("manager.url", "cloudreports.experitest.com");
        PManager.getInstance().addProperty("stream", "qachallenge");
        if (!System.getenv().containsKey("build")) {
            PManager.getInstance().addProperty("build", "debug");
        } else
            PManager.getInstance().addProperty("build", System.getenv("build"));

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
