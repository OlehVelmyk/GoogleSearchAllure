package com.google.actionHelpers;

import com.google.dataProvider.EnteredValues;
import com.google.pages.BasePage;
import com.google.pages.GoogleSearchResultPage;
import com.google.utils.DataConverter;
import io.qameta.allure.Allure;
import org.openqa.selenium.JavascriptExecutor;
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
        Allure.step("COUNT SEARCH EXPECTED RESULT = " + expectedResult);
        return expectedResult;
    }

    public int countSearchResult() {
        String text;
        int count = 0;

        ScrollSearchPageToBottom();

        List<WebElement> list = actionGetList(resultPage.getSearchBlock(), timeoutCommon);
        for (int i = 0; i < list.size(); i++) {
            text = list.get(i).getText();
            if (text.contains(EnteredValues.value)) {
                count += 1;
            }
        }
        Allure.step("COUNT SEARCH ACTUAL RESULT = " + count);
        return count;
    }

    private void ScrollSearchPageToBottom() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean reachedBottom = false;
        long lastHeight = (long) (js.executeScript("return document.body.scrollHeight"));
        while (!reachedBottom) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
            sleep(2000);
            long newHeight = (long) (js.executeScript("return document.body.scrollHeight"));
            if (newHeight == lastHeight) {
                if(!resultPage.bottomTextBlockIsPresent()) {
                    resultPage.clickMoreResultsButton();
                }else {
                    reachedBottom = true;
                }
            }
            lastHeight = newHeight;
        }
    }
}
