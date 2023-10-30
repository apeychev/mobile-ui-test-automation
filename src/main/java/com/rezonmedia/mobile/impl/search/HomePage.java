package com.rezonmedia.mobile.impl.search;

import com.rezonmedia.mobile.util.BaseCore;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class HomePage {

    private final By categoryIconsLocator = By.xpath("//div[@class='categoryIcons']");

    private final By searchFormLocator = By.xpath("//form[@name='search']");

    private final By dropDownLocator = By.xpath("//select[@name='marka']");

    private final By confirmCookiesButtonLocator = By.xpath("//button[@class='fc-button fc-cta-consent fc-primary-button']");

    private final By formSearchLocator = By.xpath("//div[@class='categoryIcons']/../../..//form[@name='search']");

    private final By searchButtonLocator = By.xpath("//div[@class='categoryIcons']/../../..//input[@type='button']");

    private final By maximalPriceInputLocator = By.xpath("//input[@name='price1']");

    private final By pageNumberLocator = By.xpath("(//span[@class='pageNumbersInfo'])[1]");

    private final By priceFirstVehicleLocator = By.xpath("((//span[@class='pageNumbersInfo'])[1]/../../../../..//span[@class='price'])[1]");

    private final By infoFirstVehicleLocator = By.xpath("((//span[@class='pageNumbersInfo'])[1]/../../../../..//a[@class='mmm'])[1]");

    private Select selectItem;

    private final BaseCore baseCore;

    public HomePage(final BaseCore baseCore) {
        this.baseCore = baseCore;
    }

    /**
     * Verify search form is displayed.
     */
    public boolean verifySearchFormIsDisplayed() {
        return baseCore.waitForElement(formSearchLocator);
    }

    public void clickConfirmCookie(){
        baseCore.waitUntilElementToBeClickable(confirmCookiesButtonLocator).click();
    }

    public void selectFromDropDown(String itemToSelect){
        baseCore.waitUntilElementToBeClickable(dropDownLocator);
        selectItem = new Select(baseCore.getDriver().findElement(dropDownLocator));
        selectItem.selectByValue(itemToSelect);
    }

    public boolean isSelectedFromDropDown(String itemToSelect){
        String firstSelectedOptionValue = selectItem.getFirstSelectedOption().getText();
        return itemToSelect.equals(firstSelectedOptionValue);
    }

    public void typeValueIntoMaximalPrice(String brandName){
        WebElement maximalPriceInputElement = baseCore.getDriver().findElement(maximalPriceInputLocator);
        maximalPriceInputElement.sendKeys(brandName);
    }

    public void clickSearchButton() {
        baseCore.waitUntilElementToBeClickable(searchButtonLocator);
        baseCore.getDriver().findElement(searchButtonLocator).click();
    }

    public boolean verifyResultsPageIsDisplayed(){
        return baseCore.waitForElement(pageNumberLocator);
    }

    public int getFirstVehiclePrice(){
        String priceOfFirstVehicle = baseCore.getDriver().findElement(priceFirstVehicleLocator).getText();

        Pattern pattern = Pattern.compile("\\d[\\d ]*");
        Matcher matcher = pattern.matcher(priceOfFirstVehicle);

        if (matcher.find()) {
            String extractedNumber = matcher.group().replaceAll(" ", "");
            return Integer.parseInt(extractedNumber);
        }
        return -1;
    }

    public String getFirstVehicleInfo(){
        return baseCore.getDriver().findElement(infoFirstVehicleLocator).getText();
    }
}


