package com.google.tests;

import com.google.pages.GoogleMainPage;
import com.google.utils.RetryAnalyzer;
import org.testng.annotations.Test;

public class N_0004_ClickSearchButtonWhenSearchFieldIsEmptyTest extends BaseTest {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void clickSearchButtonWhenSearchFieldIsEmpty() {
        GoogleMainPage page = new GoogleMainPage(driver);

        goToPage();
        page.clickSearchButtonUnderSearchFieldWhenSearchFieldIsEmpty();
    }
}
