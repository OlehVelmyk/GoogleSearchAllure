package com.google.pages;

import com.google.dataProvider.EnteredValues;
import org.openqa.selenium.*;
import org.testng.Assert;

public class GoogleMainPage extends BasePage {
    private final By searchField = By.name("q");
    private final By searchButtonUnderSearchField = By.cssSelector("center:nth-child(1) > input:nth-child(1)");
    private final By searchButtonInDropDownMenu = By.cssSelector("center:nth-child(2) > input:nth-child(1)");
    private final By emailLink = By.linkText("Gmail");

    GoogleSearchResultPage resultPage = new GoogleSearchResultPage(driver);

    public GoogleMainPage(WebDriver driver) {
        super(driver);
    }

    public boolean emailLinkIsPresent() {
        return elementIsPresent("EMAIL LINK IS PRESENT", emailLink);
    }

    public void fillSearchField() {
        actionFillField("FILL IN SEARCH FIELD", searchField, EnteredValues.value, 10);
    }

    public void clickEscapeButton() {
        actionFillField("CLICK ON ESCAPE BUTTON", searchField, Keys.ESCAPE, 10);
    }

    public void clickEnterButton() {
        actionFillField("CLICK ON ENTER BUTTON", searchField, Keys.ENTER, 10);
        Assert.assertTrue(resultPage.searchBlockIsPresent(), "Element isn't present on the page");
    }

    public void clickSearchButtonUnderSearchFieldWhenSearchFieldIsFilled() {
        actionClickElement("CLICK ON SEARCH BUTTON UNDER SEARCH FIELD", searchButtonUnderSearchField, 10);
        Assert.assertTrue(resultPage.searchBlockIsPresent(), "Element isn't present on the page");
    }

    public void clickSearchButtonUnderSearchFieldWhenSearchFieldIsEmpty() {
        actionClickElement("CLICK ON SEARCH BUTTON UNDER SEARCH FIELD", searchButtonUnderSearchField, 10);
        Assert.assertTrue(emailLinkIsPresent(), "Element isn't present on the page");
    }

    public void clickSearchButtonInDropDownMenu() {
        actionClickElement("CLICK ON SEARCH BUTTON IN DROPDOWN MENU", searchButtonInDropDownMenu, 10);
        Assert.assertTrue(resultPage.searchBlockIsPresent(), "Element isn't present on the page");
    }
}
