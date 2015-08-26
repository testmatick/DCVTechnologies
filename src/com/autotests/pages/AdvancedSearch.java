package com.autotests.pages;

import com.core.base.BasePage;
import org.openqa.selenium.WebElement;
import com.core.reporting.Reporter;

import java.util.ArrayList;
import java.util.List;

public class AdvancedSearch extends BasePage{

    public void waitForPageToLoad(){
        Reporter.log("Waiting `Advanced Search` page to load");
        waitForElementVisibility("pageIndex");
    }

    public void typeSearchingOrganizationName(String organizationName){
        type("Typing searching organization name : " + organizationName, organizationName, "orgNameInput");
    }

    public void selectSearchingOrganizationType(String organizationType){
        selectDropDownListOptionByText("Select searching organization type : " + organizationType,
                organizationType, "orgTypeSelector");
    }

    public void setExludeOrganizationTypeState(boolean state){
        setCheckboxState("Set exclude orgamization type checkbox state : " + state, state, "excludeOrgTypeCheckbox");
    }

    public void clickSearchButton(){
        click("Clicking search button", "searchButton");
    }

    public int searchedOrganizationsCount() {
        //todo write pattern and get data by it
        return 1;
    }

    public int getDisplayedSearchedOrganizationsCount(){
        Reporter.log("Get displaying searched organizations count");
        return getElementsCount("searchedRecords");
    }

    public List<String> getFoundedOranizationsTypes(){
        List<String> orgTypes = new ArrayList<>();
        for(WebElement element : getElements("searchedRecords")){orgTypes.add(element.getText());}
        return orgTypes;
    }
}
