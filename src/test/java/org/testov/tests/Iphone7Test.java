package org.testov.tests;

import content.steps.CommonSteps;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import content.pages.*;

import java.util.concurrent.TimeUnit;

public class Iphone7Test {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private ItemPage itemPage;
    private BasketPage basketPage;
//    private Actions actions;


    @Autowired
    private CommonSteps commonSteps;


    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        homePage = new HomePage();
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        itemPage = new ItemPage(driver);
        basketPage = new BasketPage(driver);
//        actions = new Actions(driver);
        commonSteps = new CommonSteps();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }


    @Test
    public void iphone7Search() {
        //логинимся на главной странице
        homePage.open(driver)
                .clickSignInLink();
        loginPage.fillEmailField("lovetest111@yandex.ru")
                .fillPasswordField("123456qW")
                .clickSignButton();

//        commonSteps.login("lovetest111@yandex.ru", "123456qW");

        //Проверяем, что удалось залогиниться
        Assert.assertEquals(homePage.getHello(), "Hello, Love");

        //Ищем iphone 7 128gb
        homePage.fillSearchField("iphone 7 128gb")
                .clickSearchButton();

        //Уочняем поиск и проверяем, что нашлось то, что нужно
        searchPage.clickCheckBox128Gb();
        Assert.assertEquals(searchPage.getFirstResult(), "\"iphone 7 128gb\"");

        //Находим самое дешевое предложение (здесь можно было бы проверить, что оно действительно самое дешевое, если знать, что у нас БД)
        searchPage.clickSortByPrice();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        searchPage.clickLowPriceItem();

        //добавляем товар в корзину и проверяем, что он добавился
        itemPage.clickAddToCartButton();
        Assert.assertTrue(itemPage.getAddToCartText().contains("Added to Basket"));

        //переходим на главную, открываем корзину, проверям наличие товара в ней и удаляем товар
        homePage.open(driver)
                .clickBasket();
        Assert.assertTrue(basketPage.checkItem().contains("iPhone 7"));
        basketPage.clickDelete();

        commonSteps.moveToElement(homePage.getSingIn());
        homePage.clickSignOut();
    }
}
