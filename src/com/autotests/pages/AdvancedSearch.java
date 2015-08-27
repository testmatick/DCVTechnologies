package com.autotests.pages;

import com.autotests.control.Pages;
import com.core.base.BasePage;
import org.openqa.selenium.WebElement;
import com.core.reporting.Reporter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdvancedSearch extends BasePage{

    public void waitForPageToLoad(){
        Reporter.log("Waiting `Advanced Search` page to load");
        switchToNewTab();
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
        waitForElementVisibility("searchLoader");
        waitForElementInvisibility("searchLoader");
    }

    public int getSearchedOrganizationsCount() {
        Pattern pattern = Pattern.compile("Results: (\\d+).*");
        Matcher matcher = pattern.matcher(getElementText("Getting searching results conclusions", "resultsConclusions"));
        return matcher.find() ? Integer.valueOf(matcher.group(1)) : 0;
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
