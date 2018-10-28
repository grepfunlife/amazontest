package content.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {

    public SearchPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public WebDriver driver;

    @FindBy(xpath = "//div[@ data-a-input-name='s-ref-checkbox-14210458031']")
    private WebElement checkBox128Gb;

    @FindBy(id = "bcKwText")
    private WebElement resultNotSort;

    @FindBy(xpath = "//select[@class='a-spacing-top-mini']/option[@value='price-asc-rank']")
    private WebElement sortByPrice;

    @FindBy(xpath = "//*[@id=\"result_0\"]/div/div[2]/div/div/a")
    private WebElement lowPriceItem1;

    @FindBy(xpath = "//*[@id=\"result_0\"]/div/div/div/div[2]/div[1]/div[1]/a")
    private WebElement lowPriceItem2;

    public void clickCheckBox128Gb() {
        checkBox128Gb.click();
    }

    public String getFirstResult() {
        String amazonsChoice = resultNotSort.getText();
        return amazonsChoice;
    }

    public void clickSortByPrice() {
        sortByPrice.click();
    }

    public void clickLowPriceItem() {
        try {
            lowPriceItem2.click();
        } catch (Exception e) {
            lowPriceItem1.click();
        }
    }
}
