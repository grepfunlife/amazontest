package content.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BasketPage {

    public BasketPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(xpath = "//*[@class='a-size-small sc-action-delete']")
    private WebElement delete;

    @FindBy(xpath = "//*[@class='a-link-normal sc-product-link']/span")
    private WebElement item;

    @FindBy(xpath = "//*[@id=\"sc-active-cart\"]/div/h1")
    private WebElement basketIsEmpty;

    public void clickDelete() {
        delete.click();
    }

    public String checkItem() {
        return item.getAttribute("innerHTML");
    }

    public String checkBasket() {
        return basketIsEmpty.getAttribute("innerHTML");
    }
}
