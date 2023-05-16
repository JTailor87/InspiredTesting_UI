package org.example;

import static org.junit.Assert.assertTrue;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AppTest 
{
    public static WebDriver driver;
    @BeforeClass
    public static void initializeTheBrowser(){
        WebDriverManager.chromedriver().setup();
        driver =new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }
    @Test
    public void AcceptAlert(){
        driver.findElement(By.xpath("//*[@id='content']/div/ul/li[1]/button")).click();
        driver.switchTo().alert().accept();
        String resultText = driver.findElement(By.xpath("//*[@id='result']")).getText();
        Assert.assertEquals(resultText, "You successfully clicked an alert");
    }
    @Test
    public void DismissAlert(){
        driver.findElement(By.xpath("//*[@id='content']/div/ul/li[2]/button")).click();
        driver.switchTo().alert().dismiss();
        String resultText = driver.findElement(By.xpath("//*[@id='result']")).getText();
        Assert.assertEquals(resultText, "You clicked: Cancel");
    }
    @Test
    public void EnterTextInPrompt(){
        driver.findElement(By.xpath("//*[@id='content']/div/ul/li[3]/button")).click();
        driver.switchTo().alert().sendKeys("Inspired Testing");
        driver.switchTo().alert().accept();
        String resultText = driver.findElement(By.xpath("//*[@id='result']")).getText();
        Assert.assertEquals(resultText, "You entered: Inspired Testing");
    }
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
}
