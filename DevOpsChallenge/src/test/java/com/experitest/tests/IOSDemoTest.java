package com.experitest.tests;

import java.net.URL;

import com.experitest.framework.BaseTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.experitest.appium.SeeTestCapabilityType;
import com.experitest.appium.SeeTestIOSDriver;
import com.experitest.appium.SeeTestIOSElement;

public class IOSDemoTest extends BaseTest {
	protected SeeTestIOSDriver<SeeTestIOSElement> driver = null;

	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='ios'") String deviceQuery) throws Exception {
		// Init application / device capabilities
//		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBankO");
		dc.setCapability(SeeTestCapabilityType.DEVICE_QUERY, deviceQuery);
		dc.setCapability(SeeTestCapabilityType.TEST_NAME, "IOSDemoTest");
		driver = new SeeTestIOSDriver<>(new URL(getProperty("url",cloudProperties)), dc);
	}

	@Test
	public void test() {
		// Enter the test code

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
