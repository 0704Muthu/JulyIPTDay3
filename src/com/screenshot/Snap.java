package com.screenshot;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

public class Snap {
	/**
	 * Concordination
	 */

	private static void screenshot(String name) {
		//Illegal state Exception
		//Session not created Exception
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rmuth\\Downloads\\chromedriver_win32 (8)\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		WebElement logo=driver.findElement(By.id("nav-logo-sprites"));
		File source=logo.getScreenshotAs(OutputType.FILE);
		File destination = new File("C:\\Users\\rmuth\\eclipse-workspace\\July\\Screenshot\\"+name+".png");
		try {
			FileHandler.copy(source, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void fullpageScreenshot() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rmuth\\Downloads\\chromedriver_win32 (8)\\chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in");
		
		Screenshot screen = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(screen.getImage(), "jpg", new File(".\\Screenshot\\fullpage.jpg"));
	}


	public static void main(String[] args) throws Throwable {
		fullpageScreenshot();

	}

}
