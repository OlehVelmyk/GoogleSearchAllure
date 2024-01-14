package com.google.tests;

import com.google.actionHelpers.CountSearchResult;
import com.google.pages.GoogleMainPage;
import org.testng.annotations.Test;

public class N_0005_CountSearchResultTest extends BaseTest {

//    @Test//(retryAnalyzer = RetryAnalyzer.class)
    public void countSearchResult() {
        GoogleMainPage page = new GoogleMainPage(driver);
        CountSearchResult searchResult = new CountSearchResult(driver);

        goToPage();
        page.fillSearchField();
        page.clickEnterButton();
        searchResult.countAndEqualsSearchResults();
    }
}
