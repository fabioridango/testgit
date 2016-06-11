package Helper;
import Driver.WebDrivers;

//Binding
public class SpecFlowBeforeAfterHelper
{
 // BeforeScenario
 public void Setup()
 {
  WebDrivers.CreateDriver();
 }

  // AfterScenario
  public void TearDown()
  {
   WebDrivers.QuitDriver();
  }
}


