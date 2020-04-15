package myntra;


import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.SelfInjection.Split;
import okio.Options;

public class MyntraTC01 {

	public static void main(String[]args) throws InterruptedException {
		
		//Disable Windownotifications
		ChromeOptions options =new ChromeOptions();
		options.addArguments("--disable-notifications");
		
		//1.open link/ getURL
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver =new ChromeDriver(options);
		driver.get(" https://www.myntra.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	
		
		//Mouse over on Women/Actions
		Actions builder =new Actions(driver);
		WebElement ele =driver.findElementByXPath("(//a[@class='desktop-main'])[2]");
		builder.moveToElement(ele).perform();
		
		// Click Jackets &coats
		driver.findElementByLinkText("Jackets & Coats").click();
		
		//Find Total Count of the items
		 String str = driver.findElementByClassName("title-count").getText();
		 System.out.println(str);
		 String text = str.replaceAll("\\D","");
		 int total =Integer.parseInt(text);
		
		//Validate the sum of categories count matches
		String strJacket =driver.findElementByXPath("//span[@class='categories-num']").getText();
		String strCoat =driver.findElementByXPath("//span[@class='categories-num']").getText();
		int totalof_jacketsandCoats =StringToInt(strJacket)	+StringToInt(strCoat);
		if (total==totalof_jacketsandCoats) {
			System.out.println("Total count matches with splitup:"+total);
		}
		
		//Check coats
		driver.findElementByXPath("//lable[text()='Coats']").click();
		Thread.sleep(3000);
		
		//Click + More option under BRAND	
		driver.findElementByXPath("//div[@class='brand-more']").click();
		Thread.sleep(3000);
		
		//Type MANGO and click Checkbox
		driver.findElementByXPath(" //input[@class ='FilterDirectory-searchInput']").sendKeys("MANGO");
		driver.findElementByXPath("//lable[@class=' common-customCheckbox']//div[1]").click();
		Thread.sleep(2000);
		
		//Close the pop-up x
		driver.findElementByXPath(" //span[@class='myntraweb-sprite FilterDirectory-close sprites-remove']").click();
		
 	//10.Confirm all the Coats are of brand MANGO
		List<WebElement> brandNameList = driver.findElementsByXPath("//h3[@class='product-brand']");
		for(WebElement eachBrand:brandNameList)
		 {
			 if(eachBrand.getText().equalsIgnoreCase("Mango")) {
				 System.out.println("Product is from Brand Mango");
			 }
			 else
			 {
				 System.out.println("Product is not from Brand Mango");
			 }
		 }
		 
		 //11.Sort by Better Discount
		 driver.findElementByXPath("//div[@class='sort-sortBy']");
		 driver.findElementByXPath("//lable[@class ='sort-label'])[3]").click();
		 Thread.sleep(2000);
		 
		 
		 //12. Find the price of first displayed item
	    List<WebElement> price = driver.findElementsByXPath("//span[@class='product-discountPercentage']");
	    System.out.println("Price is :"+price.get(0).getText());
	    
	    //13.Mouse over on size of the first item
	    driver.findElementByXPath("//span[@class='product-sizeNoInventoryPresent']");
	    builder.moveToElement(ele).perform();
	    Thread.sleep(1000);
	    
	    
	    //14.Click on WishList Now
	    driver.findElementByXPath("//span[text()='Wishlist now])[1]").click();
	    Thread.sleep(3000);
	    
	    //15.Close browser
	    driver.close();
	     
	     
	     
		 
		
		
		
		
		
		
	}

	private static int StringToInt(String strJacket) {
		// TODO Auto-generated method stub
		return 0;
	}


}
