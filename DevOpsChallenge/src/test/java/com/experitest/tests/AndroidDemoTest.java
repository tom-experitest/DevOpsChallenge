package com.experitest.tests;

import java.net.URL;

import com.experitest.framework.BaseTest;
import com.experitest.manager.client.PManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Test;

import com.experitest.appium.SeeTestAndroidDriver;
import com.experitest.appium.SeeTestAndroidElement;
import com.experitest.appium.SeeTestCapabilityType;

public class AndroidDemoTest extends BaseTest {
	protected SeeTestAndroidDriver<SeeTestAndroidElement> driver = null;

	@BeforeMethod
	public void setUp() throws Exception{
		// Init application / device capabilities
		deviceQuery = deviceQuery == null ? "@os='android'" : deviceQuery;
		dc.setCapability(SeeTestCapabilityType.DEVICE_QUERY, deviceQuery);
		dc.setCapability(SeeTestCapabilityType.TEST_NAME, "AndroidDemoTest");
		driver = new SeeTestAndroidDriver<>(new URL(getProperty("url",cloudProperties)), dc);
	}
	
	@Test
	public void test(){
		// Enter the test code
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
		PManager.getInstance().addReportFolder(driver.getReportFolder());
	}
	
}
