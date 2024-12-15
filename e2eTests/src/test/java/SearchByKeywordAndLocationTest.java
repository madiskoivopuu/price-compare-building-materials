import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//use case 3
public class SearchByKeywordAndLocationTest extends SearchBaseTest {
    @ParameterizedTest
    @CsvSource({
            "TARTU",
            "TALLINN",
            "PARNU",
            "NARVA",
            "HAAPSALU",
            "JOGEVA",
            "KEILA",
            "VILJANDI",
            "VORU",
            "VALGA"})
    void testEnterKeywordSelectLocationClickSearchShouldReturnProductsAvailableInSelectedCity(String city) {
        Response response = page.navigate(URL_TO_PAGE);
        assertEquals(response.status(), 200);

        Locator searchInput = page.locator("input[placeholder='Otsi toodet ']");
        assertThat(searchInput).isVisible();
        searchInput.fill("Kipsplaat");

        Locator locationDropdown = page.locator("div.pl-2");
        assertThat(locationDropdown).isVisible();
        locationDropdown.click();

        page.waitForSelector("input[type='checkbox'][value='TARTU']");
        Locator cityCheckbox = page.locator(String.format("input[type='checkbox'][value='%s']", city));
        assertThat(cityCheckbox).isVisible();
        cityCheckbox.click();

        Locator searchButton = page.locator("button[type='submit']");
        assertThat(searchButton).isVisible();
        searchButton.click();

        Locator loadingAnimateIcon = page.locator("text=Otsime tooteid, tänan kannatlikuse eest.");
        assertThat(loadingAnimateIcon).isVisible();

        page.waitForSelector("ul.flex.flex-col");
        Locator firstProduct = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[3]/ul/li[1]/div");

        Locator firstProductImage = firstProduct.locator("div.flex.justify-start.items-center.h-full");
        assertThat(firstProductImage).isVisible();

        //we cant assert that it actually filters to include only those products available in selected city -
        // the clients input was that if it is not available in city we show "Info puudub" (no info) so all entries all still present regardless of city was selected

        Locator firstProductPrice = firstProduct.locator("text=€");
        assertThat(firstProductPrice).isVisible();

        Locator firstProductRedirectToShopButton = firstProduct.locator("text=E-poodi");
        assertThat(firstProductRedirectToShopButton).isVisible();
    }

    //Use case 3 alternative scenario
    @Test
    void testEnterKeywordThatDoesNotReturnAnyProductsShouldShowEmptyPage() {
        Response response = page.navigate(URL_TO_PAGE);
        assertEquals(response.status(), 200);

        Locator searchInput = page.locator("input[placeholder='Otsi toodet ']");
        assertThat(searchInput).isVisible();
        searchInput.fill("ThisStringDefinitelyDoesNotReturnAnyBuildingMaterials");

        Locator locationDropdown = page.locator("div.pl-2");
        assertThat(locationDropdown).isVisible();
        locationDropdown.click();

        page.waitForSelector("input[type='checkbox'][value='TARTU']");
        Locator cityCheckbox = page.locator("input[type='checkbox'][value='TARTU']");
        assertThat(cityCheckbox).isVisible();
        cityCheckbox.click();

        Locator searchButton = page.locator("button[type='submit']");
        assertThat(searchButton).isVisible();
        searchButton.click();

        Locator loadingAnimateIcon = page.locator("text=Otsime tooteid, tänan kannatlikuse eest.");
        assertThat(loadingAnimateIcon).isVisible();

        page.waitForSelector("div.text-gray-700");

        Locator productCount = page.locator("div.text-gray-700 > p");

        assertThat(productCount).containsText("Leitud 0 toodet");
    }
}
