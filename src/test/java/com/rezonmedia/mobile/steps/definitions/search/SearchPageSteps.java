package com.rezonmedia.mobile.steps.definitions.search;

import com.rezonmedia.mobile.impl.search.HomePage;
import com.rezonmedia.mobile.util.BaseCore;
import com.rezonmedia.mobile.util.Configuration;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public final class SearchPageSteps {

    private final BaseCore baseCore;

    private final Configuration configuration;

    private final HomePage homePage;

    public SearchPageSteps(final HomePage homePage, final BaseCore baseCore, final Configuration configuration) {
        this.homePage = homePage;
        this.baseCore = baseCore;
        this.configuration = configuration;
    }

    @When("Navigate To Web Address With BaseUrl")
    public void navigateToPage() {
        String baseUrl = configuration.getMobileUrl();
        baseCore.getDriver().get(baseUrl);
        homePage.clickConfirmCookie();
    }

    @Then("Verify Home Page Is Displayed")
    public void verifyHomePageIsDisplayed() {
        Assertions.assertTrue(homePage.verifySearchFormIsDisplayed(),"Home page is not displayed");
    }

    @When("Select {string} From Car Brand Dropdown List")
    public void selectBrandFromDropdown(String brandName){
        homePage.selectFromDropDown(brandName);
    }

    @Then("Verify {string} Is Selected")
    public void verifyIsSelected(String brandName) {
        Assertions.assertTrue(homePage.isSelectedFromDropDown(brandName));
    }

    @When("I Type {string} Into MaximalPrice Text Field")
    public void typeValueIntoATextField(String brandName){
        homePage.typeValueIntoMaximalPrice(brandName);
    }

    @When("Click Search Button")
    public void clickSearchButton(){
        homePage.clickSearchButton();
    }

    @Then("Verify That Results Page Is Displayed")
    public void verifyResultsPage() {
        Assertions.assertTrue(homePage.verifyResultsPageIsDisplayed());
    }

    @Then("Verify First Vehicle Price Can Be Parsed")
    public void verifyFirstVehiclePriceCanBeParsed(){
        Assertions.assertTrue(homePage.getFirstVehiclePrice() != -1, "First vehicle price can not be parsed from html");
    }

    @Then("Verify First Vehicle Price Is Below {int}")
    public void verifyFirstVehiclePriceMatches(int firstVehiclePrice) {
        Assertions.assertTrue(homePage.getFirstVehiclePrice() <= firstVehiclePrice, "First vehicle price is higher than maximal price");
    }

    @Then("Verify Vehicle Name Is {string}")
    public void verifyVehicleNameIs(String brandName) {
        Assertions.assertTrue(homePage.getFirstVehicleInfo().contains(brandName));
    }
}