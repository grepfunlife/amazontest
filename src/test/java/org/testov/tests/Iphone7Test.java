package org.testov.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testov.pages.HomePage;
import org.testov.pages.LoginPage;
import org.testov.pages.SearchPage;

import java.util.concurrent.TimeUnit;

public class Iphone7Test {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        ;
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.amazon.co.uk/");
    }

    @Test
    public void iphone7Search() {
        homePage.clickSignInLink();
        loginPage.fillEmailField("devochka.ch@gmail.com");
        loginPage.fillPasswordField("123456qW");
        loginPage.clickSignButton();
        String text = homePage.getHello();
        Assert.assertEquals(text, "Hello, Love");
        homePage.fillSearchField("iphone 7 128gb");
        homePage.clickSearchButton();
        searchPage.clickCheckBox128Gb();
        String resultNotSort = searchPage.getFirstResult();
        Assert.assertEquals(resultNotSort, "\"iphone 7 128gb\"");
        searchPage.clickSortByPrice();
        searchPage.clickLowPriceItem();
    }
}
