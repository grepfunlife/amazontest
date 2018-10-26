package org.testov.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testov.pages.HomePage;
import org.testov.pages.ItemPage;
import org.testov.pages.LoginPage;
import org.testov.pages.SearchPage;

import java.util.concurrent.TimeUnit;

public class Iphone7Test {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private ItemPage itemPage;

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        itemPage = new ItemPage(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.amazon.co.uk/");
    }

    @AfterTest
    public void closDriver() {
//        driver.close();
    }

    @Test
    public void iphone7Search() {
        //логинимся на главной странице
        homePage.clickSignInLink();
        loginPage.fillEmailField("devochka.ch@gmail.com")
                .fillPasswordField("123456qW")
                .clickSignButton();

        //Проверяем, что удалось залогиниться
        Assert.assertEquals(homePage.getHello(), "Hello, Love");

        //Ищем iphone 7 128gb
        homePage.fillSearchField("iphone 7 128gb")
                .clickSearchButton();

        //Уочняем поиск и проверяем, что нашлось то, что нужно
        searchPage.clickCheckBox128Gb();
        Assert.assertEquals(searchPage.getFirstResult(), "\"iphone 7 128gb\"");

        //Находим самое дегевое предложение (здесь можно было бы проверить, что оно действительно самое дешевое, если знать, что у нас БД)
        searchPage.clickSortByPrice();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        searchPage.clickLowPriceItem();

        itemPage.clickAddToCartButton();
    }
}
