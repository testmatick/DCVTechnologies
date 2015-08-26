package com.autotests.tests;


import com.autotests.control.Actions;
import com.autotests.control.Pages;
import com.core.base.BaseTest;
import com.core.utils.Constants;
import com.core.utils.Random;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class VerifyLoginPage extends BaseTest{

    @Test
    public void correctLoginTest(){
        Actions.loginActions().doLogin(Constants.DEFAULT_USER_LOGIN, Constants.DEFAULT_USER_PASSWORD);
        Pages.homePage().waitForPageToLoad();
    }

    @Test(dependsOnMethods = "correctLoginTest")
    public void logOutTest(){
        Actions.loginActions().doLogout();
    }

    @Test(dependsOnMethods = "logOutTest")
    public void loginWithIncorrectPasswordTest(){
        Actions.loginActions().doLogin(Constants.DEFAULT_USER_LOGIN, Constants.DEFAULT_USER_PASSWORD + Random.genString(2));
        Assert.assertTrue(Pages.loginPage().isErrorVisible(), "Error message is not appeared");
    }

    @Test(dependsOnMethods = "loginWithIncorrectPasswordTest")
    public void restorePasswordForIncorrectUsernameTest(){
        Actions.loginActions().restoreForgottenPassword(Random.genString(5));
        Assert.assertTrue(Pages.loginPage().isErrorVisible(), "Error message is not appeared");
    }



}
