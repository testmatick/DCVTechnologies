package com.autotests.tests;


import com.autotests.control.Actions;
import com.autotests.control.Pages;
import com.autotests.testdata.SearchData;
import com.core.base.BaseTest;
import com.core.utils.Constants;
import com.core.utils.Random;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyAdvancedSearch extends BaseTest{

    SearchData searchData;

    @Test
    public void correctLoginTest(){
        Actions.loginActions().doLogin(Constants.DEFAULT_USER_LOGIN, Constants.DEFAULT_USER_PASSWORD);
        Pages.homePage().waitForPageToLoad();
    }

    @Test(dependsOnMethods = "correctLoginTest")
    public void verifySearchByOrgName(){
        searchData = new SearchData();
        searchData.setOrganizationName("My Test Company");

        Actions.advancedSearchActions().goToAdvancedSearch();
        Actions.advancedSearchActions().doSearch(searchData);
        Assert.assertTrue(Pages.advancedSearch().getSearchedOrganizationsCount() > 0,
                "System did not find matching results");
    }

    @Test(dependsOnMethods = "verifySearchByOrgName")
    public void verifyExcludeOrganizationTypeSearchOptionTest(){
        int previousResults = Pages.advancedSearch().getSearchedOrganizationsCount();
        String orgType = "Partnership";
        searchData.setOrganizationType(orgType);
        searchData.setExcludeOrganizationType(true);
        Actions.advancedSearchActions().doSearch(searchData);

        Assert.assertTrue(Pages.advancedSearch().getSearchedOrganizationsCount() > 0,
                "System did not find matching results");

        Assert.assertTrue(previousResults > Pages.advancedSearch().getSearchedOrganizationsCount(),
                "Exclude option did not affected to the search results");

        for(String result : Pages.advancedSearch().getFoundedOranizationsTypes()) {
            Assert.assertFalse(result.contains(orgType), "Excluded organization type founded in a search results");
        }

    }


}
