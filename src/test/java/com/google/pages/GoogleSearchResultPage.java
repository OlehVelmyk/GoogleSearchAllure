package com.google.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleSearchResultPage extends BasePage {
    private final By searchBlock = By.cssSelector("#rso>div");
    private final By nextButton = By.cssSelector("#pnnext > span:nth-child(2)");
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

    public boolean nextButtonIsPresent() {
        return elementIsPresent("NEXT BUTTON IS PRESENT", nextButton);
    }

    public void clickNextButton() {
        actionClickElement("CLICK ON NEXT BUTTON", nextButton, 10);
    }

    public String getTextWithResultCounter() {
        return actionGetText(bottomTextBlock, 10);
    }
}
