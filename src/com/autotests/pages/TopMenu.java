package com.autotests.pages;

import com.core.base.BasePage;

public class TopMenu extends BasePage{

    public void clickLogOutButton(){
        click("Clicking log out button", "loginOutLink");
    }

}
