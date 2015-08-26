package com.autotests.control;

import com.autotests.actions.AdvancedSearchActions;
import com.autotests.actions.LoginActions;

public class Actions {

    private static Actions actions;

    private LoginActions loginActions;
    private AdvancedSearchActions advancedSearchActions;

    private Actions() {
        this.loginActions = new LoginActions();
        this.advancedSearchActions = new AdvancedSearchActions();
    }

    public static void setupActions() {
        actions = new Actions();
    }

    public static LoginActions loginActions() {
        return actions.loginActions;
    }

    public static AdvancedSearchActions advancedSearchActions() {return actions.advancedSearchActions;}
}
