package org.testov.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ItemPage {

    public ItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(id = "addToCart_feature_div")
    private WebElement addToCartButton;

//    @FindBy(xpath = "//*[@id=\"huc-v2-order-row-confirm-text\"]/h1")
    @FindBy(xpath = "//*[@id=\"attachDisplayAddBaseAlert\"]/div/h4")
    private WebElement addToCartText;

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public String getAddToCartText() {
        String addToBasket = addToCartText.getText();
        return addToBasket;
    }
}
