    package SpreeTest;

    import SpreePages.BasePage;
    import SpreePages.HomePage;
    import SpreePages.LoginPage;
    import com.aventstack.extentreports.Status;
    import org.testng.Assert;
    import org.testng.annotations.DataProvider;
    import org.testng.annotations.Test;

    import java.util.concurrent.TimeUnit;

    public class LoginTests extends BaseTests{

        @Test(dataProvider = "userDetail")
        public void verifyUserLoginWithCorrectCredentials(String username,String password){

            //Report
             //reportUtils.createATestCase("verifyUserLoginWithCorrectCredentials");

             // Add Test Log
            //reportUtils.addTestLog(Status.INFO,"Performing Login");

            //HomePage: User Clicks the Login Link
             HomePage homePage = new HomePage(driver);
             homePage.navigateToUrl();
             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
             homePage.clickLoginLink();

             //Login Page : User Logs in the application
             LoginPage loginPage = new LoginPage(driver);
             loginPage.loginToApplication(username,password);
             String actualmsg = loginPage.verifyLogInSpree();
             String expectedmsg = "Logged in successfully";
             //reportUtils.addTestLog(Status.INFO,"Comparing expected and Actual message");
             Assert.assertEquals(actualmsg,expectedmsg,"Test Case is Passed");

             //User Logsout of the application
           String actuallogoutmsg = loginPage.logOutSpree();
           String expectedlogoutmsg = "Signed out successfully.";
           Assert.assertEquals(actuallogoutmsg,expectedlogoutmsg,"Test Case is Passed");

        }

       /* Login into the Spree Application using valid Credentials */
        @DataProvider
        public Object[][] userDetail() {
            return new Object[][]{
                    {"sonal.mishra.it@gmail.com", "tester2020$"}
            };
        }

     //2.*Search for "Ruby" and make sure all search results have Ruby in the name */
        @Test(dataProvider = "searchName")
        public void searchProductByName(String sName) {
            HomePage homePg = new HomePage(driver);
            homePg.navigateToUrl();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            Boolean checkSearchRuby = homePg.verifyallSearchedHaveRuby();
            Assert.assertTrue(checkSearchRuby);
            homePg.clickHomeOnHomepage();
        }

        @DataProvider
        public Object[][] searchName() {
            return new Object[][]{
                    {"Ruby"}
            };
        }

      /*2. Search for "Apache" and make sure all search results have Apache in the name */
        @Test (dataProvider ="searchValue")
        public void searchProductByKeyword(String keyword) {
            HomePage homePg = new HomePage(driver);
            homePg.navigateToUrl();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            homePg.searchKeyword(keyword);
            Boolean checkSearchApache = homePg.verifyallSearchedHaveApache();
            Assert.assertTrue(checkSearchApache);
        }
        @DataProvider
        public Object[][] searchValue() {
            return new Object[][]{
                    {"Apache"}
            };
        }

       /* 3.Filter products
        1. select "Bags" in Shop by Categories
        select "$15.00 - $18.00" price range
        3. Apply filter (search)
        4. Assert products displayed matched criteria
       If possible make above data driven */
        @Test
        public void searchByFilter() throws InterruptedException {
            HomePage homePg = new HomePage(driver);
            homePg.navigateToUrl();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
            homePg.searchByFilters();
            boolean checkSearchByFilter = homePg.verifySearchByFilters();
            Assert.assertTrue(checkSearchByFilter);
        }

       /*4. Add
        1. Login
        2. search "Ruby on Rails Mug"
        3. "Ruby on Rails Mug" to cart
        4. navigate to Checkout page and validate order amount*/
          @Test(dataProvider = "userDetail")
        public void validateAmountInTheCart(String email,String password) throws InterruptedException {
              HomePage homePg = new HomePage(driver);
              homePg.navigateToUrl();
              homePg.clickLoginLink();
              LoginPage loginPage = new LoginPage(driver);
              loginPage.loginToApplication(email, password);

              homePg.searchKeyword("Ruby on Rails Mug");
              String expectedMugPrice= loginPage.copyMugPrice();
              loginPage.addToCart();
              driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
              String actualMugPrice = loginPage.validateCartAmount();
              Assert.assertEquals(actualMugPrice,expectedMugPrice,"Test Case is Passed");

          }

    /*5. Clear Cart
        1. Login
        2. add 3 "Ruby on Rails Mug" to cart
        3. Remove "Ruby on Rails Mug" from cart
        4. validate "Your cart is empty" message*/

              @Test (dataProvider ="userDetail")
           public void addAndDeleteFromTheCart(String email,String password) throws InterruptedException {
                  HomePage homePg = new HomePage(driver);
                  homePg.navigateToUrl();
                  homePg.clickLoginLink();
                  LoginPage loginPage = new LoginPage(driver);
                  loginPage.loginToApplication(email, password);
                  homePg.searchKeyword("Ruby on Rails Mug");
                  String emptyCartMsg=loginPage.verifyAndDeleteProductInCart();
                  loginPage.deleteProductInCart();
                  Assert.assertEquals(emptyCartMsg,"Your cart is empty");
                  loginPage.logOutSpree();
              }

        }


