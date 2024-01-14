package com.google.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultPage extends BasePage {
    private final By searchBlock = By.cssSelector("div.MjjYud");
    private final By moreResultsButton = By.cssSelector("div > a > h3 > div");
    private final By bottomTextBlock = By.id("ofr");

    public GoogleSearchResultPage(WebDriver driver) {
        super(driver);
    }

    public By getSearchBlock() {
        return searchBlock;
    }

    public boolean searchBlockIsPresent() {
        return elementIsPresent("SEARCH BLOCK IS PRESENT", searchBlock);
    }

    public void clickMoreResultsButton() {
        actionClickElement("CLICK ON MORE RESULTS BUTTON", moreResultsButton, timeoutCommon);
    }

    public boolean bottomTextBlockIsPresent() {
        return elementIsPresent("BOTTOM TEXT BLOCK IS NOT PRESENT", bottomTextBlock);
    }

    public String getTextWithResultCounter() {
        return actionGetText(bottomTextBlock, timeoutCommon);
    }
}
