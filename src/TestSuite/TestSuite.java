package TestSuite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Driver.WebDrivers;
import Helper.SpecFlowBeforeAfterHelper;
import Helper.RomanNumberGeneratorHelper;



public class TestSuite {

	public static void main(String[] args) {
	
	// Create Driver
	WebDrivers.CreateDriver();
		
    // Load browser
    WebDriver driver = null;
	try {
		driver = WebDrivers.GetDriver();
		
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	// Maximize browser
    driver.manage().window().maximize();
  
	//Test case GoogleCalculator
    TestSuite TestGoogleCalculator = new TestSuite();
    try {
		TestGoogleCalculator.GoogleCalculator(driver);
	} catch (Exception e) {
		e.printStackTrace();
	}
    
	
	//Test case AmazonBasket
    TestSuite TestAmazonBasket = new TestSuite();
    try {
		TestAmazonBasket.AmazonBasket(driver);
	} catch (Exception e) {
    	e.printStackTrace();
	}
    
    
  //Test case Wikipedia
    TestSuite TestWikipedia = new TestSuite();
    try {
    	TestWikipedia.Wikipedia(driver);
	} catch (Exception e) {
		e.printStackTrace();
	}
       
    //Close Web site
  	WebDrivers.QuitDriver();
 	
}

	public void GoogleCalculator(WebDriver driver) throws Exception { 
		
	String [] arithmeticFormula = { "4+9", "9-4", "4/2","4*2"};	
	String [] arithmeticDefine = { "arithmetic addition", "arithmetic subtraction,", "arithmetic multiplication","arithmetic division"};			
	int [] arithmeticResult = { 13, 5, 2, 8};			

	int totalArithmeticFormula = 4;

	for(int index = 0 ; index < totalArithmeticFormula ; index++  ){
		
	 // Open Application
	 driver.get("https://www.google.com/ncr "); 

	 // Search for arithmetic addition
	 driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys(arithmeticFormula[index]);
	 driver.findElement(By.xpath("//*[@id='sblsbb']")).click();
	 Thread.sleep(1000);
	 int calculationResult = Integer.parseInt(driver.findElement(By.xpath("//*[@id='cwtltblr']/div[2]")).getText());
	
	 // Confirm that the calculation result
	 if (calculationResult != arithmeticResult[index]){
		
	  throw new Exception("calculation result of the "+arithmeticDefine[index]+" is wrong");
		
	 }
    }
   }
	
	public void AmazonBasket(WebDriver driver) throws Exception { 
				
	 // Open Application
	 driver.get("http://www.amazon.co.uk/"); 
	 
	 // wait element text area 
	 WebElement textAreaElement = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='twotabsearchtextbox']")));
	 driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).sendKeys("Cucumber");
	 driver.findElement(By.xpath("//*[@id='nav-search']/form/div[2]/div/input")).click();
		 
     // Click on the second item result
	 driver.findElement(By.xpath("//*[@id='result_1']/div/div/div/div[2]/div[2]/a/h2")).click();
	 
	 // productTitle
	 //WebElement productTitleElement = (new WebDriverWait(driver, 60)).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='productTitle']")));
	 String productTitle = driver.findElement(By.xpath("//*[@id='productTitle']")).getText();
	
	 
	 // Add the item to the basket
	 driver.findElement(By.xpath("//*[@id='add-to-cart-button']")).click();
	 Thread.sleep(1000);
	
	 
	 // Edit Basket
	 driver.findElement(By.xpath("//*[@id='hlb-view-cart-announce']")).click();
	 Thread.sleep(1000);
	 
	 // productTitle
	 String productTitleBasket = driver.findElement(By.xpath("//*[@id='activeCartViewForm']/div[2]/div/div[4]/div[2]/div[1]/div/div/div[2]/ul/li[1]/span/a/span")).getText();

	 // Verify that the chosen item is present in the basket
	 
	 if (!productTitle.equals(productTitleBasket)){
		 
	  throw new Exception("the product chosen is not present in the basket");
	  
	 }
	}

	public void Wikipedia(WebDriver driver) throws Exception { 
			
	// Open Application
	driver.get("https://en.wikipedia.org/wiki/Main_Page"); 
	Thread.sleep(1000);
		 
	//select the first entry ‘On this day
	driver.findElement(By.xpath("//*[@id='mp-otd']/ul[1]/li[2]/a[1]")).click();
	Thread.sleep(500);
		 
	// year selected
    int yearSelected = Integer.parseInt(driver.findElement(By.xpath("//*[@id='firstHeading']")).getText());
		 
    // Retrieve the Roman Numerals
    String romanNumerals = driver.findElement(By.xpath("//*[@id='mw-content-text']/p[1]/b/a")).getText();
		
    // Check if the the Roman Numerals match with the year selected
	if(!romanNumerals.equals(RomanNumberGeneratorHelper.RomanNumerals(yearSelected)) ){
		throw new Exception(" The Roman Numerals no match with the year selected");	
	}
   }
}
