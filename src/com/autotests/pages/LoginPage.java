package com.autotests.pages;

import com.core.base.BasePage;
import com.core.reporting.Reporter;
import com.core.utils.Constants;

public class LoginPage extends BasePage {

    public void openLoginPage(){
        driver.get(Constants.BASE_URL);
    }

    public void waitForPageToLoad(){
        Reporter.log("Waiting `Login` page loads");
        waitForElementPresent("pageIndex");
    }

    public void typeLogin(String login) {
        waitForElementVisibility("loginInput");
        type("Typing login : " + login, login, "loginInput");
    }

    public void typePassword(String password) {
        type("Typing password : " + password, password, "loginPassword");
    }

    public void clickLoginButton() {
        click("Clicking login button", "loginButton");
    }

    public boolean isErrorVisible(){
        return !getElementText("Checking error message visibility", "errorMessage").isEmpty();
    }

    public void clickForgottenPasswordLink(){
        click("Clicking forgotten password link", "forgottenPasswordLink");
        waitForElementPresent("pageIndex");
    }

    public void typeRestoringPasswordUsername(String username){
        type("Typing restoring password username : " + username, username, "forgottenPassUsernameInput");
    }

    public void clickSubmitRestorePasswordButton(){
        click("Clicking submit restore password button", "restorePasswordButton");
        waitForPageToLoad();
    }

}
