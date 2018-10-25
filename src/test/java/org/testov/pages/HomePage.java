package org.testov.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(id = "nav-link-yourAccount")
    private WebElement signIn;

    @FindBy(xpath = "//a[@id='nav-link-yourAccount']/span")
    private WebElement privet;

    @FindBy(id = "twotabsearchtextbox")
    private WebElement searchField;

    @FindBy(className = "nav-input")
    private WebElement searchButton;

    public void clickSignInLink() {
        signIn.click();
    }

    public String getHello() {
        String hello = privet.getText();
        return hello;
    }

    public void fillSearchField(String order) {
        searchField.sendKeys(order);
    }

    public void clickSearchButton() {
        searchButton.click();
    }
}
