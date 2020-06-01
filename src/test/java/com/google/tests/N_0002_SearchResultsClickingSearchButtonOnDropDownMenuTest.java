package com.google.tests;

import com.google.pages.GoogleMainPage;
import com.google.utils.RetryAnalyzer;
import org.testng.annotations.Test;

public class N_0002_SearchResultsClickingSearchButtonOnDropDownMenuTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void searchResultsClickingSearchButtonOnDropDownMenu() {
        GoogleMainPage page = new GoogleMainPage(driver);

        goToPage();
        page.fillSearchField();
        page.clickSearchButtonInDropDownMenu();
    }
}
