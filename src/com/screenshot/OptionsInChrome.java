package com.screenshot;

import org.openqa.selenium.chrome.*;

public class OptionsInChrome {
	
	static ChromeOptions chromeoption;
	
	private static void setup() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rmuth\\Downloads\\chromedriver_win32 (8)\\chromedriver.exe");
		chromeoption = new ChromeOptions();
		chromeoption.addArguments("incognito");
		chromeoption.addArguments("start-maximized");
		chromeoption.addArguments("headless");
		browserlaunch();
	}

	private static void browserlaunch() {
		ChromeDriver driver = new ChromeDriver(chromeoption);
		driver.get("https://www.amazon.in");
		String title = driver.getTitle();
		System.out.println(title);
	}

	public static void main(String[] args) {
		setup();
	}
	
}
