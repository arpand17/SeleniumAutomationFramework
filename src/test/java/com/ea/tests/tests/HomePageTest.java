package com.ea.tests.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.ea.framework.base.BaseTest;
import com.ea.framework.datareader.ReadTestData;
import com.ea.framework.logger.LoggerManager;
import com.ea.tests.pages.HomePage;

public final class HomePageTest extends BaseTest{
	
	private HomePageTest() {
		
	}
	
	@Test
	private void verifyLoginPageNavigation() throws Exception
	{
		HomePage hp = new HomePage();
		hp.clickOnLoginLink();
		String actualTitle = hp.getTitle();
		Assert.assertEquals(actualTitle,ReadTestData.getTestData("expected"));
		
	}

}
