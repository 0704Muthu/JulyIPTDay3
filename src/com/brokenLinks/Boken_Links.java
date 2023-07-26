package com.brokenLinks;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Boken_Links {

	static ChromeOptions chromeoption;
	static ChromeDriver driver;
	static String url;
	static HttpURLConnection huc;
	static int responseCode;
	static List<WebElement> links;
	static 	String homepage="https://www.amazon.in";

	private static void browserSetUp() {
		
		chromeoption = new ChromeOptions();
		chromeoption.addArguments("start-maximized");
		chromeoption.addArguments("incognito");
		browserLaunch();
	}


	private static void browserLaunch() {
		//Illegal StateException--->Key,value have any error include means
		//Session not created Exception---->If chrome updated
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\rmuth\\Downloads\\chromedriver_win32 (8)\\chromedriver.exe");
		driver = new ChromeDriver(chromeoption);
		driver.get(homepage);
		driver.findElement(By.cssSelector("[type=\"text\"]")).sendKeys("book");
		connectionSetup();
	}

	private static void connectionSetup() {
		url="";
		huc=null;
		responseCode=200;
		findingLinks();
	}

	private static void findingLinks() {
		links = driver.findElements(By.tagName("a"));
		validateLinks();
	}
	//[1,2,3,4]
	//For Each
	//Iterator
	private static void validateLinks() {
		
		Iterator<WebElement> iterator = links.iterator();
		while(iterator.hasNext()) {
			url = iterator.next().getAttribute("href");
			if (url==null||url.isEmpty()) {
				System.out.println("anchor tag is empty");	
				continue;
			}
			if (!url.startsWith(homepage)) {
				System.out.println("URL belong to another domain,Skipp the url");
				continue;
			}

			try {
				huc=(HttpURLConnection)(new URL(url).openConnection());
				huc.setRequestMethod("HEAD");
				huc.connect();
				responseCode = huc.getResponseCode();
				System.out.println(responseCode);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (responseCode>=400) {
				System.err.println(url+"is a broken link");
			} else {
				System.out.println(url+"is a Valid link");
			}
		}
	}
	public static void main(String[] args) {
		browserSetUp();
	}

}
