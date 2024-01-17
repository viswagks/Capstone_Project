package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import utilities.Utility;

import java.util.List;

public class StepDefinitions extends Utility {

    @Given("I am on home page")
    public void launch() {
        navigateTo("http://the-internet.herokuapp.com/");
    }

    @Then("I verify the title")
    public void verifyTitle() {
        String title = getElement(By.xpath("//h1")).getText();
        Assert.assertEquals(title, "Welcome to the-internet");
    }

    @When("I click on {string} link")
    public void clickLink(String link) {
        String xpath = null;
        switch (link)
        {
            case "A/B Testing":
                xpath = "//a[text()='A/B Testing']";
                break;
            case "Dropdown":
                xpath = "//a[text()='Dropdown']";
                break;
            case "Frames":
                xpath = "//a[text()='Frames']";
                break;
        }
        clickElement(By.xpath(xpath));
    }

    @Then("I verify one of following text is present in page")
    public void verifyText(List<String> list) {
        StringBuilder expText = new StringBuilder();
        for (String s : list)
            expText.append(" or text() = '").append(s).append("'");

        String xpath = "//*[" + expText.toString().replaceFirst(" or ", "") + "]";
        WebElement text = getDriver().findElement(By.xpath(xpath));
        Assert.assertTrue(text.isDisplayed(), "Text " + expText + " must be displayed");
    }

    @Then("I navigate back")
    public void navigate_back() {
        getDriver().navigate().back();
    }

    @Then("I select {string} from {string}")
    public void select_value(String value, String dropdown) {
        selectFromDropdown(By.id(dropdown), value);
    }

    @Then("I verify {string} is selected in {string}")
    public void verify_value(String value, String dropdown) {
        selectFromDropdown(By.id(dropdown), value);
    }

    @Then("I verify all of below {string} are present in page")
    public void verifyLinks(String type, List<String> list) {
        String xpath;
        for (String s : list) {
            if (type.equalsIgnoreCase("links"))
                xpath = "//a[text()='" + s + "']";
            else
                xpath = "//*[text()='" + s + "']";
            WebElement text = getDriver().findElement(By.xpath(xpath));
            Assert.assertTrue(text.isDisplayed(), type + " " + s + " must be displayed");
        }
    }

    @Then("I quit")
    public void driver_quit() {
        getDriver().quit();
    }
}
