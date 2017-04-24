package com.experitest.tests;

import java.net.URL;

import com.experitest.framework.BaseTest;
import com.experitest.manager.client.PManager;
import org.testng.annotations.*;

import com.experitest.appium.SeeTestAndroidDriver;
import com.experitest.appium.SeeTestAndroidElement;
import com.experitest.appium.SeeTestCapabilityType;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;



public class LoginTest extends BaseTest {
	protected SeeTestAndroidDriver<SeeTestAndroidElement> driver = null;
	
	@BeforeMethod
	public void setUp(@Optional("@os='android'") String deviceQuery) throws Exception{

		deviceQuery = deviceQuery == null ? "@os='android" : deviceQuery;
		dc.setCapability(MobileCapabilityType.APP, "cloud:com.experitest.ExperiBank/.LoginActivity");
		dc.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.experitest.ExperiBank");
		dc.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ".LoginActivity");
		dc.setCapability(SeeTestCapabilityType.DEVICE_QUERY, deviceQuery);
		dc.setCapability(SeeTestCapabilityType.TEST_NAME, "LoginTest");
		driver = new SeeTestAndroidDriver<>(new URL(getProperty("url",cloudProperties)), dc);
	}
	
	@Test
	public void test(){

		driver.findElement(in.Repo.obj("login.usernameTextField")).sendKeys("company");
		driver.findElement(in.Repo.obj("login.passwordTextField")).sendKeys("company");
		driver.findElement(in.Repo.obj("login.Login")).click();
		driver.findElement(in.Repo.obj("main.MakePayment"));
		driver.findElement(in.Repo.obj("main.Logout")).click();

	}
	
	@AfterMethod
	public void tearDown(){
		driver.quit();
		PManager.getInstance().addReportFolder(driver.getReportFolder());

	}
	
}
