package com.google.pages;

import com.google.dataProvider.EnteredValues;
import org.openqa.selenium.*;
import org.testng.Assert;

public class GoogleMainPage extends BasePage {
    private final By searchField = By.name("q");
    private final By searchButtonUnderSearchField = By.cssSelector("center:nth-child(1) > input:nth-child(1)");
    private final By searchButtonInDropDownMenu = By.cssSelector("center:nth-child(2) > input:nth-child(1)");
    private final By emailLink = By.linkText("Gmail");

    private final By searchPopup= By.id("Alh6id");

    GoogleSearchResultPage resultPage = new GoogleSearchResultPage(driver);

    public GoogleMainPage(WebDriver driver) {
        super(driver);
    }

    public boolean emailLinkIsPresent() {
        return elementIsPresent("EMAIL LINK IS PRESENT", emailLink);
    }

    public void fillSearchField() {
        actionFillField("FILL IN SEARCH FIELD", searchField, EnteredValues.value, timeoutCommon);
        waitForClickable(searchPopup, timeoutCommon);
    }

    public void clickEscapeButton() {
        actionFillField("CLICK ON ESCAPE BUTTON", searchField, Keys.ESCAPE, timeoutCommon);
    }

    public void clickEnterButton() {
        actionFillField("CLICK ON ENTER BUTTON", searchField, Keys.ENTER, timeoutCommon);
        sleep(1000);
        Assert.assertTrue(resultPage.searchBlockIsPresent(), "Element isn't present on the page");
        Assert.assertEquals(actionGetText(searchField, timeoutCommon), EnteredValues.value);
    }

    public void clickSearchButtonUnderSearchFieldWhenSearchFieldIsFilled() {
        actionClickElement("CLICK ON SEARCH BUTTON UNDER SEARCH FIELD", searchButtonUnderSearchField, timeoutCommon);
        Assert.assertTrue(resultPage.searchBlockIsPresent(), "Element isn't present on the page");
        Assert.assertEquals(actionGetText(searchField, timeoutCommon), EnteredValues.value);
    }

    public void clickSearchButtonUnderSearchFieldWhenSearchFieldIsEmpty() {
        actionClickElement("CLICK ON SEARCH BUTTON UNDER SEARCH FIELD", searchButtonUnderSearchField, timeoutCommon);
        Assert.assertTrue(emailLinkIsPresent(), "Element isn't present on the page");
    }

    public void clickSearchButtonInDropDownMenu() {
        actionClickElement("CLICK ON SEARCH BUTTON IN DROPDOWN MENU", searchButtonInDropDownMenu, timeoutCommon);
        Assert.assertTrue(resultPage.searchBlockIsPresent(), "Element isn't present on the page");
        Assert.assertEquals(actionGetText(searchField, timeoutCommon ), EnteredValues.value);
    }

}
