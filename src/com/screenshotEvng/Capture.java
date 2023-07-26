package com.screenshotEvng;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Capture {

	static ChromeOptions chromeoption;
	static ChromeDriver driver;
	private static void browserSetUp() {
		chromeoption = new ChromeOptions();
		chromeoption.addArguments("start-maximized");
		chromeoption.addArguments("incognito");
		//chromeoption.addArguments("headless");
		String browserName = chromeoption.getBrowserName();
		System.out.println(browserName);
		browserLaunch();
	}
	private static void browserLaunch() {
		//Illegal StateException--->Key,value have any error include means
		//Session not created Exception---->If chrome updated
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rmuth\\Downloads\\chromedriver_win32 (8)\\chromedriver.exe");
		driver = new ChromeDriver(chromeoption);
		driver.get("https://www.amazon.in");
		driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("book");
	}
	//.exe -->Executable File -->
	private static void getALogo() {
		WebElement logo=driver.findElement(By.id("nav-logo-sprites"));
		File source = logo.getScreenshotAs(OutputType.FILE);
		File destination=new File(".\\Screenshot\\amz.png");
		try {
			FileHandler.copy(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void fullPagescreenshot() {
		Screenshot screen = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		try {
			ImageIO.write(screen.getImage(), "jpg",new File(".\\Screenshot\\fullpage.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		browserSetUp();
		
	}

}
