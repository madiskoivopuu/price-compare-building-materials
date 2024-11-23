import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.microsoft.playwright.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

//use case 4
public class SearchByCategoryTest extends SearchBaseTest {
    @Test
    void testSelectCategoryClickSearchShouldReturnProducts() {
        page.navigate(URL_TO_PAGE);

        page.click("text=Ehitusplaadid");
        page.click("text=Kipsplaat");

        Locator loadingSpinIcon = page.locator("#root > div.w-full.h-max.flex.bg-gray-100 > div.w-full.md\\:w-3\\/4.h-max.md\\:p-10.p-4 > div.w-full.h-max > div > svg");
        assertThat(loadingSpinIcon).isVisible();

        page.waitForSelector("ul.flex.flex-col");
        Locator firstProduct = page.locator("#root > div.w-full.h-max.flex.bg-gray-100 > div.w-full.md\\:w-3\\/4.h-max.md\\:p-10.p-4 > div.w-full.h-max > ul > li:nth-child(1) > div");

        Locator firstProductImage = firstProduct.locator("div.flex.justify-start.items-center.h-full");
        assertThat(firstProductImage).isVisible();

        Locator firstProductPrice = firstProduct.locator("text=€");
        assertThat(firstProductPrice).isVisible();

        Locator firstProductQuantity = firstProduct.locator("text=TK");
        assertThat(firstProductQuantity).isVisible();

        Locator firstProductAvailabilityButton = firstProduct.locator("text=Saadavus");
        assertThat(firstProductAvailabilityButton).isVisible();

        Locator firstProductRedirectToShopButton = firstProduct.locator("text=E-poodi");
        assertThat(firstProductRedirectToShopButton).isVisible();
    }

    @ParameterizedTest
    @CsvSource({
            "Ehitusplaadid, Kipsplaat",
            "Ehitusplaadid, OSB-plaat",
            "Ehitusplaadid, Vineer",
            "Ehitusplaadid, Tsementkiudplaat",
            "Ehitusplaadid, Puitkiudplaat",
            "Ehitusplaadid, Puitlaastplaat",
            "Ehitusplaadid, Kipskiudplaat (Fermacell)",
            "Ehitusplaadid, WEDI plaat",
            "Ehitusplaadid, TYCROC plaat",
            "Plokid ja kivid, Poorbetoon (bauroc)",
            "Plokid ja kivid, Fibo",
            "Plokid ja kivid, Õõnesplokk",
            "Plokid ja kivid, Silikaatplokk (silroc)",
            "Plokid ja kivid, Lakkaplokk",
            "Plokid ja kivid, Keraterm",
            "Plokid ja kivid, Vundamendiplokk",
            "Plokid ja kivid, Tellis",
            "Plokid ja kivid, Sillused",
            "Plokid ja kivid, Tänavakivi, teekivi",
            "Plokid ja kivid, Kõnniteeplaat",
            "Plokid ja kivid, Äärekivid",
            "Plokid ja kivid, Vihmaveerenn",
            "Plokid ja kivid, Korstnad",
            "Soojustus, Kivivill",
            "Soojustus, Klaasvill",
            "Soojustus, Tselluvill",
            "Soojustus, EPS",
            "Soojustus, PIR",
            "Soojustus, XPS",
            "Soojustus, Tuuletõkkeplaat",
            "Puit, Servamata",
            "Puit, Liimpuit",
            "Puit, Põrandalauad",
            "Puit, Terrassilauad",
            "Puit, Sisevoodrilauad",
            "Segud valmis/kuiv, Pahtel",
            "Segud valmis/kuiv, Krohv",
            "Segud valmis/kuiv, Müürisegud",
            "Segud valmis/kuiv, Plaatimissegud",
            "Segud valmis/kuiv, Vuugisegud",
            "Segud valmis/kuiv, Ahjusegud",
            "Segud valmis/kuiv, Tsement",
            "Segud valmis/kuiv, Betoon",
            "Segud valmis/kuiv, Liim",
            "Segud valmis/kuiv, Põrandatasandus",
            "Segud valmis/kuiv, Tarvikud",
            "Segud valmis/kuiv, Sideained",
            "Fassaad, Plastvooder",
            "Fassaad, Profiilid",
            "Fassaad, Tarvikud",
            "Katused, Teras",
            "Katused, Eterniit",
            "Katused, Bituumen",
            "Katused, PVC/PC",
            "Katused, Vihmaveesüsteemid",
            "Katused, Tarvikud",
            "Kattematerjalid, Ehituspaber",
            "Kattematerjalid, Kangad",
            "Kattematerjalid, Kiled",
            "Kattematerjalid, Aurutõke",
            "Kattematerjalid, Geotekstiil",
            "Kattematerjalid, Katuse aluskate",
            "Ehitusmetall, Armatuur",
            "Ehitusmetall, Plekkprofiilid"
    })
    void testAreAllCategoriesAvailable(String category, String subCategory) {
        page.navigate(URL_TO_PAGE);

        page.click(String.format("text=%s",category));

        Locator subCategoryButton = page.locator(String.format("text=%s",subCategory));
        assertTrue(subCategoryButton.isVisible());
    }
}
