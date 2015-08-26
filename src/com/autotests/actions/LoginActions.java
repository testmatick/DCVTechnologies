package com.autotests.actions;

import com.autotests.control.Pages;
import com.core.base.BaseActions;

public class LoginActions extends BaseActions {

    public void doLogin(String login, String password){
        Pages.loginPage().openLoginPage();
        Pages.loginPage().waitForPageToLoad();
        Pages.loginPage().typeLogin(login);
        Pages.loginPage().typePassword(password);
        Pages.loginPage().clickLoginButton();
    }

    public void doLogout(){
        Pages.topMenu().clickLogOutButton();
        Pages.loginPage().waitForPageToLoad();
    }

    public void restoreForgottenPassword(String username){
        Pages.loginPage().waitForPageToLoad();
        Pages.loginPage().clickForgottenPasswordLink();
        Pages.loginPage().typeRestoringPasswordUsername(username);
        Pages.loginPage().clickSubmitRestorePasswordButton();
    }



}
