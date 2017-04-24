package com.experitest.auto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;

import com.experitest.appium.SeeTestCapabilityType;
import com.experitest.manager.client.PManager;

public class BaseTest {

	protected DesiredCapabilities dc = new DesiredCapabilities();
	protected Properties cloudProperties = new Properties();
	public void init() throws Exception{
//		System.out.println("User Dir : " + System.getProperty("user.dir"));
		initCloudProperties();
		File reporterDir = new File(System.getProperty("user.dir"),"reports");
		reporterDir.mkdirs();
        dc.setCapability(SeeTestCapabilityType.REPORT_DIRECTORY, reporterDir.getAbsolutePath());
        dc.setCapability(SeeTestCapabilityType.REPORT_FORMAT, "xml");
        dc.setCapability(SeeTestCapabilityType.USE_REMOTE_GRID, true);
        dc.setCapability(SeeTestCapabilityType.USERNAME, getProperty("griduser", cloudProperties));
        dc.setCapability(SeeTestCapabilityType.PASSWORD, getProperty("gridpass", cloudProperties));
        // In case your user is assign to a single project leave empty, otherwise please specify the project name
        dc.setCapability(SeeTestCapabilityType.PROJECT_NAME, getProperty("project", cloudProperties));
        if(!System.getenv().containsKey("build")){
             PManager.getInstance().addProperty("build", "debug");
        }
        System.setProperty("manager.url", "cloudreports.experitest.com");

	}

	protected String getProperty(String property, Properties props) throws FileNotFoundException, IOException{

		if(System.getProperty(property) != null){
			return System.getProperty(property);
		} else if(System.getenv().containsKey(property)){
			return System.getenv(property);
		} else if(props != null){
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
