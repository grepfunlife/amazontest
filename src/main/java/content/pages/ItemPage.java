package content.pages;

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

    @FindBy(xpath = "//*[@id='attachDisplayAddBaseAlert']/div/h4")
    private WebElement addToCartText;

    @FindBy(xpath = "//*[@id='huc-v2-order-row-confirm-text']/h1")
    private WebElement addToCartText2;

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public String getAddToCartText() {
        try {
            return addToCartText.getAttribute("innerHTML");
        } catch (Exception e) {
            return addToCartText2.getAttribute("innerHTML");
        }
    }
}
