package com.ea.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ea.framework.base.BaseTest;
import com.ea.framework.datareader.ReadTestData;
import com.ea.tests.pages.LoginPage;

public final class LoginPageTest extends BaseTest{

	
	private LoginPageTest()
	{
		
	}
	
	@Test
	private void loginTest() throws Exception
	{
		System.out.println(ReadTestData.getTestData("testname"));
		LoginPage lp = new LoginPage();
		String actualTitle = lp.enterUserName(ReadTestData.getTestData("username"))
							   //.enterPassword(ReadTestData.getTestData("password"))
							   .enterPassword("password1")
							   .clickLoginButton()
							   .getTitle();
		Assert.assertEquals(actualTitle, ReadTestData.getTestData("expected"));
		
		
	}
}
