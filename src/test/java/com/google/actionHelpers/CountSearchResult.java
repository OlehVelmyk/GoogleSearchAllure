package com.google.actionHelpers;

import com.google.dataProvider.EnteredValues;
import com.google.logging.CustomReporter;
import com.google.pages.BasePage;
import com.google.pages.GoogleSearchResultPage;
import com.google.utils.DataConverter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.List;

public class CountSearchResult extends BasePage {

    public CountSearchResult(WebDriver driver) {
        super(driver);
    }

    GoogleSearchResultPage resultPage = new GoogleSearchResultPage(driver);

    public void countAndEqualsSearchResults() {
        Assert.assertEquals(countSearchResult(), getResultCounter());
    }

    public int getResultCounter() {
        int expectedResult = Integer.parseInt(DataConverter.parseTextValue(resultPage.getTextWithResultCounter(),
                "\\d+"));
        CustomReporter.logCheckCount("COUNT SEARCH EXPECTED RESULT = " + expectedResult);
        return expectedResult;
    }

    public int countSearchResult() {
        int generalCount = countSearchResultOnOnePage();
        while (resultPage.nextButtonIsPresent()) {
            resultPage.clickNextButton();
            generalCount += countSearchResultOnOnePage();
        }
        CustomReporter.logCheckCount("COUNT SEARCH ACTUAL RESULT = " + generalCount);
        return generalCount;
    }

    public int countSearchResultOnOnePage() {
        String text;
        int count = 0;
        List<WebElement> list = actionGetList(resultPage.getSearchBlock(), 10);
        for (int i = 0; i < list.size(); i++) {
            text = list.get(i).getText();
            if (text.toLowerCase().contains(EnteredValues.value)) {
                count += 1;
            }
        }
        CustomReporter.logCheckCount("COUNT SEARCH ACTUAL RESULT ON ONE PAGE = " + count);
        return count;
    }
}
