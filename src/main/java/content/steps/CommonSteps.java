package content.steps;

import content.pages.HomePage;
import content.pages.LoginPage;
import framework.steps.Step;
import framework.steps.Steps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.beans.factory.annotation.Autowired;

@Steps
public class CommonSteps {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage = new HomePage();

    @Step("Навестись на элемент")
    public void moveToElement(WebElement webElement) {
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).build().perform();
    }

    @Step("Логин")
    public void login(String account, String password) {
        homePage.open(driver)
                .clickSignInLink();
        loginPage.fillEmailField(account)
                .fillPasswordField(password)
                .clickSignButton();
    }
}
