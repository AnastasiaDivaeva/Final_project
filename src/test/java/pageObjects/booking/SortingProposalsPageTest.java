package pageObjects.booking;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

public class SortingProposalsPageTest {

    @AfterMethod
    public void closeDriver(){
        Selenide.closeWebDriver();
    }

    @Test
    public void sortingProposalPageTest() {
        HomePage homePage = new HomePage();
        homePage.openHomePage();
        homePage.closePopUp(WebDriverRunner.getWebDriver());
        SortingProposalPage sortingProposalPage = new SortingProposalPage();
        sortingProposalPage.setCityInSearchBar("Львів");
        sortingProposalPage.setDateInSearchBar("2023-11-05", "2023-11-10");
        sortingProposalPage.clickOnSubmitSearchButton();
        sortingProposalPage.chooseSortByPriceAsc();
        ElementsCollection setEstimatesAfterFilter = sortingProposalPage.getPrices();

        List<Integer> pricesAscOrder = setEstimatesAfterFilter.stream()
                .map(priceText -> priceText.getText()
                        .replaceAll("UAH", "")
                        .replaceAll(" ", ""))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        for (int i = 1; i < pricesAscOrder.size(); i++) {
            Assert.assertTrue(pricesAscOrder.get(i - 1) <= pricesAscOrder.get(i));
        }
    }
}
