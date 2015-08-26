package com.autotests.pages;


import com.core.base.BasePage;
import com.core.reporting.Reporter;
import com.core.utils.Constants;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.SessionNotCreatedException;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class LoginPage extends BasePage {

    public void waitForPageToLoad(){
        Reporter.log("Waiting `Login` page loads");
        waitForElementPresent("loginFlow");
    }

    public void typeLogin(String login) {
        waitForElementVisibility("loginField");
        type("Typing login : " + login, login, "loginField");
    }

    public void typePassword(String password) {
        type("Typing password : " + password, password, "passwordField");
    }

    public void clickLoginButton() {
        click("Clicking login button", "signInButton");
        waitForElementInvisibility("loader");
    }

    public boolean isPageOpened(){
        Reporter.log("Checking `Login` page opened");
        return isElementPresent("loginFlow");
    }

    public boolean isErrorVisible(){
        Reporter.log("Checking error message visibility");
        return isElementVisible("errorMessage");
    }

    public String getErrorText(){
        return getElementText("Getting error text", "errorMessage");
    }

    public void setRememberMeCheckboxState(boolean state){
        setCheckboxState("Set remember me checkbox checked: " + state, state, "rememberMeCheckbox");
    }

}
