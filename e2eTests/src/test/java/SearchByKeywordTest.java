import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.microsoft.playwright.Locator;

import com.microsoft.playwright.Response;
import org.junit.jupiter.api.Test;

//Use case 1
public class SearchByKeywordTest extends SearchBaseTest {
    @Test
    void testEnterKeywordClickSearchShouldReturnProductWithSpecifiedElements() {
        Response response = page.navigate(URL_TO_PAGE);
        assertEquals(response.status(), 200);

        Locator searchInput = page.locator("input[placeholder='Otsi toodet ']");
        assertThat(searchInput).isVisible();
        searchInput.fill("Kipsplaat Knauf");

        Locator searchButton = page.locator("button[type='submit']");
        assertThat(searchButton).isVisible();
        searchButton.click();

        Locator loadingAnimateIcon = page.locator("text=Otsime tooteid, tänan kannatlikuse eest.");
        assertThat(loadingAnimateIcon).isVisible();

        page.waitForSelector("ul.flex.flex-col");
        Locator firstProduct = page.locator("//*[@id=\"root\"]/div[2]/div[2]/div[3]/ul/li[1]/div");

        Locator firstProductImage = firstProduct.locator("div.flex.justify-start.items-center.h-full");
        assertThat(firstProductImage).isVisible();

        Locator firstProductPrice = firstProduct.locator("text=€");
        assertThat(firstProductPrice).isVisible();

        Locator firstProductAvailabilityButton = firstProduct.locator("text=Saadavus");
        assertThat(firstProductAvailabilityButton).isVisible();

        Locator firstProductRedirectToShopButton = firstProduct.locator("text=E-poodi");
        assertThat(firstProductRedirectToShopButton).isVisible();
    }

    //Use case 1 alternative scenario
    @Test
    void testEnterKeywordThatDoesNotReturnAnyProductsShouldShowEmptyPage() {
        Response response = page.navigate(URL_TO_PAGE);
        assertEquals(response.status(), 200);

        Locator searchInput = page.locator("input[placeholder='Otsi toodet ']");
        assertThat(searchInput).isVisible();
        searchInput.fill("ThisStringDefinitelyDoesNotReturnAnyBuildingMaterials");

        Locator searchButton = page.locator("button[type='submit']");
        assertThat(searchButton).isVisible();
        searchButton.click();

        Locator loadingAnimateIcon = page.locator("text=Otsime tooteid, tänan kannatlikuse eest.");
        assertThat(loadingAnimateIcon).isVisible();

        page.waitForSelector("div.text-gray-700");

        Locator productCount = page.locator("div.text-gray-700 > p");

        assertThat(productCount).containsText("Leitud 0 toodet");
    }

    @Test
    void testEmptyKeywordShouldDoNothing() {
        Response response = page.navigate(URL_TO_PAGE);
        assertEquals(response.status(), 200);

        Locator searchInput = page.locator("input[placeholder='Otsi toodet ']");
        assertThat(searchInput).isVisible();
        searchInput.fill("");

        Locator searchButton = page.locator("button[type='submit']");
        assertThat(searchButton).isVisible();
        searchButton.click();

        Locator loadingAnimateIcon = page.locator("text=Otsime tooteid, tänan kannatlikuse eest.");
        assertThat(loadingAnimateIcon).not().isVisible();
    }
}
