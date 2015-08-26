package com.autotests.tests;


import com.autotests.control.Actions;
import com.autotests.control.Pages;
import com.core.base.BaseTest;
import com.core.utils.Constants;
import com.core.utils.Random;
import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyHomePageContent extends BaseTest{

    @Test
    public void correctLoginTest(){
        Actions.loginActions().doLogin(Constants.DEFAULT_USER_LOGIN, Constants.DEFAULT_USER_PASSWORD);
        Pages.homePage().waitForPageToLoad();
    }

    @Test(dependsOnMethods = "correctLoginTest")
    public void checkManagementInformationBoxTest(){
        Assert.assertTrue(Pages.homePage().isInternalDataManagementButtonPresent(),
                "Internal data management button present is not present on a main page");
        Assert.assertTrue(Pages.homePage().isExternalDataManagementButtonPresent(),
                "External data management button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isCaseManagementButtonPresent(),
                "Case management button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isReportsButtonPresent(),
                "Reports button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isAdvancedSearchButtonPresent(),
                "Advanced search button is not present on a main page");
    }

    @Test(dependsOnMethods = "correctLoginTest")
    public void checkOperationalManagementBoxContent(){
        Assert.assertTrue(Pages.homePage().isCaseFlowManagementButtonPresent(),
                "Case flow management button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isAssignCasesButtonPresent(),
                "Assign cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isProfileRecordsButtonPresent(),
                "Profile records button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSystemAdministratorButtonPresent(),
                "System administrator button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isAssignCasesToRemoveButtonPresent(),
                "Assign cases to remove button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isOpMyAlertsPresent(),
                "Operational management my alerts button is not present on a main page");
    }

    @Test(dependsOnMethods = "correctLoginTest")
    public void checkSupervisorBoxContentTest(){
        Assert.assertTrue(Pages.homePage().isSupervisorAssignCasesButtonPresent(),
                "Supervisor assign cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorBulkAuthoriseMatchedCasesButtonPresent(),
                "Supervisor bulk authorise matched cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorAuthoriseCasesValidRecordsButtonPresent(),
                "Supervisor authorise cases valid records button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorMatchesRequiringFurtherReviewButtonPresent(),
                "Supervisor matches requiring further review button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorRecordsIncorrectlyEnteredButtonPresent(),
                "Supervisor records incorrectly entered button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorAuthoriseCasesInactiveRecordButtonPresent(),
                "Supervisor authorise cases inactive record button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorAuthoriseCasesNeedsMoreInformationButtonPresent(),
                "Supervisor authorise cases needs more information button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorAuthoriseCasesMatchedToInactiveButtonPresent(),
                "Supervisor authorise cases matched to inactive button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorAuthoriseCasesToBeRemovedButtonPresent(),
                "Supervisor authorise cases to be removed button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorMyAlertsFirstButtonPresent(),
                "Supervisor my alerts first button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorDataExtractAssignCasesButtonPresent(),
                "Supervisor data extract assign cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorChangedExternalValueCasesButtonPresent(),
                "Supervisor changed external value cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorDifferentExternalValueCasesButtonPresent(),
                "Supervisor different external value cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorMultipleExternalValueCasesButtonPresent(),
                "Supervisor multiple external value cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorSingleExternalValueCasesButtonPresent(),
                "Supervisor single external value cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorFormatErrorCasesButtonPresent(),
                "Supervisor format error cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isSupervisorAttributeCasesAlertButtonPresent(), "Supervisor attribute cases alert button is not present on a main page");
    }

    @Test(dependsOnMethods = "correctLoginTest")
    public void checkProcessorBoxContentTest(){
        Assert.assertTrue(Pages.homePage().isProcessorAllMyCasesButtonPresent(),
                "Processor all my cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isProcessCasesButtonPresent(), "" +
                "Process cases button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isProcessorProfileRecordsButtonPresent(),
                "Processor profile records button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isProcessorCasesRequiredMoreInformationButtonPresent(),
                "Processor cases required more information button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isProcessorTasksListButtonPresent(),
                "Processor tasks list button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isProcessorEmailButtonPresent(),
                "Processor email button is not present on a main page");
        Assert.assertTrue(Pages.homePage().isProcessorMyAlertsButtonPresent(),
                "Processor my alerts button is not present on a main page");
    }




}
