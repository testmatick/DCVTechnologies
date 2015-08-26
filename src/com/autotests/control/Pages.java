package com.autotests.control;

import com.autotests.pages.*;

import java.util.AbstractCollection;

public class Pages {
    private static Pages pages;

    private LoginPage loginPage;
    private TopMenu topMenu;
    private AdvancedSearch advancedSearch;
    private HomePage homePage;

    private Pages() {
        this.loginPage = new LoginPage();
        this.topMenu = new TopMenu();
        this.advancedSearch = new AdvancedSearch();
        this.homePage = new HomePage();
    }

    public static void setupPages() {
        pages = new Pages();
    }

    public static LoginPage loginPage() {
        return pages.loginPage;
    }

    public static TopMenu topMenu(){return pages.topMenu;}

    public static AdvancedSearch advancedSearch() {return pages.advancedSearch;}

    public static HomePage homePage(){return pages.homePage;}


}
