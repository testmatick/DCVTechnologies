package com.autotests.pages;

import com.core.base.BasePage;
import com.core.reporting.Reporter;

public class HomePage extends BasePage{

    public void waitForPageToLoad(){
        Reporter.log("Waiting `Home` page loads");
        waitForElementVisibility("pageIndex");
    }

    public void clickAdvancedSearchButton(){
        click("Clicking advanced search button", "internalDataManagementButton");
    }

    public boolean isInternalDataManagementButtonPresent(){
        Reporter.log("Checking internal data management button present on a home page");
        return isElementPresent("internalDataManagementButton");
    }

    public boolean isExternalDataManagementButtonPresent(){
        Reporter.log("Checking external data management button present on a home page");
        return isElementPresent("externalDataManagementButton");
    }

    public boolean isCaseManagementButtonPresent(){
        Reporter.log("Checking case management button present on a home page");
        return isElementPresent("caseManagementButton");
    }

    public boolean isReportsButtonPresent(){
        Reporter.log("Checking reports button present on a home page");
        return isElementPresent("reportsButton");
    }

    public boolean isAdvancedSearchButtonPresent(){
        Reporter.log("Checking advanced search button present on a home page");
        return isElementPresent("advancedSearchButton");
    }

    public boolean isCaseFlowManagementButtonPresent(){
        Reporter.log("Checking case flow management button present on a home page");
        return isElementPresent("caseFlowManagementButton");
    }

    public boolean isAssignCasesButtonPresent(){
        Reporter.log("Checking assign cases button present on a home page");
        return isElementPresent("assignCasesButton");
    }

    public boolean isProfileRecordsButtonPresent(){
        Reporter.log("Checking profile records button present on a home page");
        return isElementPresent("profileRecordsButton");
    }

    public boolean isSystemAdministratorButtonPresent(){
        Reporter.log("Checking system administrator button present on a home page");
        return isElementPresent("systemAdministratorButton");
    }

    public boolean isAssignCasesToRemoveButtonPresent(){
        Reporter.log("Checking assign cases to remove button present on a home page");
        return isElementPresent("assignCasesToRemoveButton");
    }

    public boolean isOpMyAlertsPresent(){
        Reporter.log("Checking operational management my alerts button present on a home page");
        return isElementPresent("opMyAlerts");
    }

    public boolean isSupervisorAssignCasesButtonPresent(){
        Reporter.log("Checking supervisor assign cases button present on a home page");
        return isElementPresent("supervisorAssignCasesButton");
    }

    public boolean isSupervisorBulkAuthoriseMatchedCasesButtonPresent(){
        Reporter.log("Checking supervisor bulk authorise matched cases button present on a home page");
        return isElementPresent("supervisorBulkAuthoriseMatchedCasesButton");
    }

    public boolean isSupervisorAuthoriseCasesValidRecordsButtonPresent(){
        Reporter.log("Checking supervisor authorise cases valid records button present on a home page");
        return isElementPresent("supervisorAuthoriseCasesValidRecordsButton");
    }

    public boolean isSupervisorMatchesRequiringFurtherReviewButtonPresent(){
        Reporter.log("Checking supervisor matches requiring further review button present on a home page");
        return isElementPresent("supervisorMatchesRequiringFurtherReviewButton");
    }

    public boolean isSupervisorRecordsIncorrectlyEnteredButtonPresent(){
        Reporter.log("Checking supervisor records incorrectly entered button present on a home page");
        return isElementPresent("supervisorRecordsIncorrectlyEnteredButton");
    }

    public boolean isSupervisorAuthoriseCasesInactiveRecordButtonPresent(){
        Reporter.log("Checking supervisor authorise cases inactive record button present on a home page");
        return isElementPresent("supervisorAuthoriseCasesInactiveRecordButton");
    }

    public boolean isSupervisorAuthoriseCasesNeedsMoreInformationButtonPresent(){
        Reporter.log("Checking supervisor authorise cases needs more information button present on a home page");
        return isElementPresent("supervisorAuthoriseCasesNeedsMoreInformationButton");
    }

    public boolean isSupervisorAuthoriseCasesMatchedToInactiveButtonPresent(){
        Reporter.log("Checking supervisor authorise cases matched to inactive button present on a home page");
        return isElementPresent("supervisorAuthoriseCasesMatchedToInactiveButton");
    }

    public boolean isSupervisorAuthoriseCasesToBeRemovedButtonPresent(){
        Reporter.log("Checking supervisor authorise cases to be removed button present on a home page");
        return isElementPresent("supervisorAuthoriseCasesToBeRemovedButton");
    }

    public boolean isSupervisorMyAlertsFirstButtonPresent(){
        Reporter.log("Checking supervisor my alerts first button present on a home page");
        return isElementPresent("supervisorMyAlertsFirstButton");
    }

    public boolean isSupervisorDataExtractAssignCasesButtonPresent(){
        Reporter.log("Checking supervisor data extract assign cases button present on a home page");
        return isElementPresent("supervisorDataExtractAssignCasesButton");
    }

    public boolean isSupervisorChangedExternalValueCasesButtonPresent(){
        Reporter.log("Checking supervisor changed external value cases button present on a home page");
        return isElementPresent("supervisorChangedExternalValueCasesButton");
    }

    public boolean isSupervisorDifferentExternalValueCasesButtonPresent(){
        Reporter.log("Checking supervisor different external value cases button present on a home page");
        return isElementPresent("supervisorDifferentExternalValueCasesButton");
    }

    public boolean isSupervisorMultipleExternalValueCasesButtonPresent(){
        Reporter.log("Checking supervisor multiple external value cases button present on a home page");
        return isElementPresent("supervisorMultipleExternalValueCasesButton");
    }

    public boolean isSupervisorSingleExternalValueCasesButtonPresent(){
        Reporter.log("Checking supervisor single external value cases button present on a home page");
        return isElementPresent("supervisorSingleExternalValueCasesButton");
    }

    public boolean isSupervisorFormatErrorCasesButtonPresent(){
        Reporter.log("Checking supervisor format error cases button present on a home page");
        return isElementPresent("supervisorFormatErrorCasesButton");
    }

    public boolean isSupervisorAttributeCasesAlertButtonPresent(){
        Reporter.log("Checking supervisor attribute cases alert button present on a home page");
        return isElementPresent("supervisorAttributeCasesAlertButton");
    }

    public boolean isProcessorAllMyCasesButtonPresent(){
        Reporter.log("Checking processor all my cases button present on a home page");
        return isElementPresent("processorAllMyCasesButton");
    }

    public boolean isProcessCasesButtonPresent(){
        Reporter.log("Checking process cases button present on a home page");
        return isElementPresent("processCasesButton");
    }

    public boolean isProcessorProfileRecordsButtonPresent(){
        Reporter.log("Checking processor profile records button present on a home page");
        return isElementPresent("processorProfileRecordsButton");
    }

    public boolean isProcessorCasesRequiredMoreInformationButtonPresent(){
        Reporter.log("Checking processor cases required more information button present on a home page");
        return isElementPresent("processorCasesRequiredMoreInformationButton");
    }

    public boolean isProcessorTasksListButtonPresent(){
        Reporter.log("Checking processor tasks list button present on a home page");
        return isElementPresent("processorTasksListButton");
    }

    public boolean isProcessorEmailButtonPresent(){
        Reporter.log("Checking processor email button present on a home page");
        return isElementPresent("processorEmailButton");
    }

    public boolean isProcessorMyAlertsButtonPresent(){
        Reporter.log("Checking processor my alerts button present on a home page");
        return isElementPresent("processorMyAlertsButton");
    }


}
