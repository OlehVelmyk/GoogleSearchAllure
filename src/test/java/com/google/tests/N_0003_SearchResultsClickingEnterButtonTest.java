package com.google.tests;

import com.google.pages.GoogleMainPage;
import com.google.utils.RetryAnalyzer;
import org.testng.annotations.Test;

public class N_0003_SearchResultsClickingEnterButtonTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void searchResultsClickingEnterButton() {
        GoogleMainPage page = new GoogleMainPage(driver);

        goToPage();
        page.fillSearchField();
        page.clickEnterButton();
    }
}
