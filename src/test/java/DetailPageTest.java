import org.testng.annotations.Test;

public class DetailPageTest {
    @Test
    public void checkThatPriceWhenBookingIsSame(){
        String city = "Львів";
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp();
        homePage.searchCity(city);
        homePage.clickSearchButton();
        homePage.closePopUPData();
        SearchResultPage searchResultPage = new SearchResultPage();

    }

}
