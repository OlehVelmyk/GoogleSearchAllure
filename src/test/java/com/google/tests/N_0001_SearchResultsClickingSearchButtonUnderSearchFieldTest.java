package com.google.tests;

import com.google.pages.GoogleMainPage;
import com.google.utils.RetryAnalyzer;
import org.testng.annotations.Test;

public class N_0001_SearchResultsClickingSearchButtonUnderSearchFieldTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void searchResultsClickingSearchButtonUnderSearchField() {
        GoogleMainPage page = new GoogleMainPage(driver);

        goToPage();
        page.fillSearchField();
        page.clickEscapeButton();
        page.clickSearchButtonUnderSearchFieldWhenSearchFieldIsFilled();
    }
}
