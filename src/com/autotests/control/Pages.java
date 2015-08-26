package com.autotests.control;

import com.autotests.pages.*;

import java.util.AbstractCollection;

public class Pages {
    private static Pages pages;

    private LoginPage loginPage;

    private Pages() {
        this.loginPage = new LoginPage();
    }

    public static void setupPages() {
        pages = new Pages();
    }

    public static LoginPage loginPage() {
        return pages.loginPage;
    }


}
