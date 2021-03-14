package SpreePages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage{

    @FindBy (xpath ="//input[@id ='spree_user_email']")
    private WebElement useremail;

    @FindBy(css = "#spree_user_password")
    private WebElement password;

    @FindBy(xpath = "//input[@value='Login']")
    private WebElement loginButton;

    @FindBy(xpath ="//a[@href='/logout']")
    private WebElement logout;

    @FindBy(xpath = "//div[@id='content']/div")
    WebElement successMessage;

    @FindBy(xpath = "//div[@class='alert alert-notice']")
    WebElement logOutMessage;

    @FindBy(xpath = "//span[@title='Ruby on Rails Mug']")
    WebElement selectProduct;

    @FindBy(css = "button[id='add-to-cart-button']")
    WebElement addToCart;

    @FindBy(how = How.XPATH, using = "//span[@class = 'price selling lead' and @content='13.99']")
    WebElement mugPrice;

    @FindBy(how=How.XPATH,using = "//td[@class = 'lead']")
    WebElement cartAmount;

    @FindBy(how=How.XPATH,using = "//input[@class = 'form-control line_item_quantity']")
    WebElement productQuantity;

    @FindBy(how=How.XPATH,using = "//input [@type = 'submit' and @value='Search'] ")
    WebElement emptyCart;

    @FindBy(how = How.XPATH,using = "//div[@class='alert alert-info']")
    WebElement emptyCartMsg;

    @FindBy(how = How.XPATH, using = "//h1")
    WebElement shoppingTitle;

    public LoginPage(WebDriver driver){
        super(driver);
    }

    public void loginToApplication(String username,String passwrd)
    {
        useremail.sendKeys(username);
        password.sendKeys(passwrd);
        loginButton.click();
    }

    public String verifyLogInSpree(){
        String success = successMessage.getText();
        return success;
    }

    public String logOutSpree() {
        logout.click();
        String logOutMsg = logOutMessage.getText();
          return logOutMsg;
    }

    public void addToCart(){
        selectProduct.click();
        addToCart.click();

    }

    public String copyMugPrice()
    {
        String mugP = mugPrice.getText();
        return mugP;
    }
   public String validateCartAmount()
    {
        String cartA = cartAmount.getText();
          return cartA;
    }

    public void deleteProductInCart(){
        emptyCart.click();
    }

    public void deleteIfAnyItemIsAlreadyAdded(){
       String productQtyText = productQuantity.getAttribute("value");
        int productQty = Integer.parseInt(productQtyText);
        if (productQty>=1)
        deleteProductInCart();
    }

    public String verifyAndDeleteProductInCart()
    {
        String productQtyText = productQuantity.getText();
        System.out.println("Product Quantity text" + productQtyText);
        int productQty = Integer.parseInt(productQtyText);
        while(productQty <= 3)
           addToCart();
            System.out.println("\n 3 Ruby on Rails Mug were present in the cart");
        String emptyMsg=emptyCartMsg.getText();
        System.out.println("\n Empty Cart Message"+emptyCartMsg);
        return emptyMsg;
    }


}
