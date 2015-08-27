package com.autotests.actions;


import com.autotests.control.Pages;
import com.autotests.testdata.SearchData;
import com.core.base.BaseActions;


public class AdvancedSearchActions extends BaseActions {

    public void goToAdvancedSearch(){
        Pages.homePage().clickAdvancedSearchButton();
        Pages.advancedSearch().waitForPageToLoad();
    }

    public void doSearch(SearchData searchData){

        if(searchData.getOrganizationName() != null)
            Pages.advancedSearch().typeSearchingOrganizationName(searchData.getOrganizationName());

        if(searchData.getOrganizationType() != null){
            Pages.advancedSearch().selectSearchingOrganizationType(searchData.getOrganizationType());
            Pages.advancedSearch().setExludeOrganizationTypeState(searchData.isExcludeOrganizationType());
        }

        Pages.advancedSearch().clickSearchButton();
    }



}
