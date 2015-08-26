package com.autotests.control;

import com.autotests.actions.MainActions;

public class Actions {

    private static Actions actions;

    private MainActions mainActions;

    private Actions() {
        this.mainActions = new MainActions();
    }

    public static void setupActions() {
        actions = new Actions();
    }

    public static MainActions mainActions() {
        return actions.mainActions;
    }
}
