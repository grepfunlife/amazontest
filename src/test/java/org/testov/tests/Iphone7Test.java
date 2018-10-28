package org.testov.tests;

import content.pages.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static java.lang.System.setProperty;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.Reporter.log;

public class Iphone7Test {
    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private SearchPage searchPage;
    private ItemPage itemPage;
    private BasketPage basketPage;
    private Actions actions;

    @BeforeClass
    public void setup() {
        setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        actions = new Actions(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        searchPage = new SearchPage(driver);
        itemPage = new ItemPage(driver);
        basketPage = new BasketPage(driver);
    }

    @AfterTest
    public void closeDriver() {
        driver.close();
    }

    @Test
    public void iphone7Search() {
        final String login = "lovetest111@yandex.ru";
        final String password = "123456qW";
        final String user = "Love";
        final String item = "iphone 7 128gb";
        //логинимся на главной странице
        homePage.open()
                .clickSignInLink();
        loginPage.logIn(login, password);

        //Проверяем, что удалось залогиниться
        assertEquals(homePage.getHello(), "Hello, " + user);
        log("Логин пользователя " + user + " прошел успешно");

        //Ищем iphone 7 128gb
        homePage.fillSearchField(item)
                .clickSearchButton();

        //УТочняем поиск и проверяем, что нашлось то, что нужно
        searchPage.clickCheckBox128Gb();
        assertTrue(searchPage.getFirstResult().contains(item));
        log("Поиск по " + item + " выполнен успешно");

        //Находим самое дешевое предложение (здесь можно было бы проверить, что оно действительно самое дешевое, если знать, что у нас БД)
        searchPage.clickSortByPrice();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        searchPage.clickLowPriceItem();

        //добавляем товар в корзину и проверяем, что он добавился
        itemPage.clickAddToCartButton();
        assertTrue(itemPage.getAddToCartText().contains("Added to Basket"));
        log("Получено сообщение, что " + item + " уcпешно добавлен в корзину");

        //переходим на главную, открываем корзину, проверям наличие товара в ней и удаляем товар
        homePage.open()
                .clickBasket();
        assertTrue(basketPage.checkItem().contains("iPhone 7"));
        log(item + " находится в корзине");
        basketPage.clickDelete();
        assertTrue(basketPage.checkBasket().contains("Your Shopping Basket is empty"));
        log("Товар удален из корзины");

        //Выходим из аккаунта
        actions.moveToElement(homePage.getSingIn()).build().perform();
        homePage.clickSignOut();
        homePage.open();
        assertEquals(homePage.getHello(), "Hello. Sign in");
        log("Выход из аккаунта прошел успешно");
    }
}
