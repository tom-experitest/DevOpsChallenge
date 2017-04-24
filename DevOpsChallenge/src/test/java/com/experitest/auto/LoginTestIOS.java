package com.experitest.auto;

import java.net.URL;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.experitest.appium.SeeTestCapabilityType;
import com.experitest.appium.SeeTestIOSDriver;
import com.experitest.appium.SeeTestIOSElement;

import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class LoginTestIOS extends BaseTest {
	protected SeeTestIOSDriver<SeeTestIOSElement> driver = null;

	@BeforeMethod
	@Parameters("deviceQuery")
	public void setUp(@Optional("@os='ios'") String deviceQuery) throws Exception {
		init();
		// Init application / device capabilities
//		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBankO");
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBankO");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBankO");
		dc.setCapability(SeeTestCapabilityType.DEVICE_QUERY, deviceQuery);
		dc.setCapability(SeeTestCapabilityType.TEST_NAME, "IOSDemoTest");
		driver = new SeeTestIOSDriver<>(new URL(getProperty("url",cloudProperties)), dc);
	}

	@Test
	public void test() {
		driver.findElement(in.Repo.obj("login_ios.Username")).sendKeys("company");
		driver.findElement(in.Repo.obj("login_ios.Password")).sendKeys("company");
		driver.findElement(in.Repo.obj("login_ios.loginButton")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
