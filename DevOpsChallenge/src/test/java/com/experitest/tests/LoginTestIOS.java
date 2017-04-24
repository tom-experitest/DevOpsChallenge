package com.experitest.tests;

import java.net.URL;

import com.experitest.framework.BaseTest;
import com.experitest.manager.client.PManager;
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
	public void setUp() throws Exception {
		// Init application / device capabilities
		deviceQuery = deviceQuery == null ? "@os='ios'" : deviceQuery;
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBankO");
		dc.setCapability(IOSMobileCapabilityType.BUNDLE_ID, "com.experitest.ExperiBankO");
		dc.setCapability(SeeTestCapabilityType.DEVICE_QUERY, deviceQuery);
		dc.setCapability(SeeTestCapabilityType.TEST_NAME, "LoginTestIOS");
		driver = new SeeTestIOSDriver<>(new URL(getProperty("url",cloudProperties)), dc);
	}

	@Test
	public void test() {
		driver.findElement(in.Repo.obj("login_ios.Username")).sendKeys("company");
		driver.findElement(in.Repo.obj("login_ios.Password")).sendKeys("company");
		driver.findElement(in.Repo.obj("login_ios.loginButton")).click();
		driver.findElement(in.Repo.obj("main_ios.makePaymentButton"));
		driver.findElement(in.Repo.obj("main_ios.logoutButton")).click();

	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
        PManager.getInstance().addReportFolder(driver.getReportFolder());
    }

}
