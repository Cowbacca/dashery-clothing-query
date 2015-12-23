package uk.co.dashery.products;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import uk.co.dashery.clothing.Clothing;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;
import static uk.co.dashery.ClothingTestUtils.createClothing;
import static uk.co.dashery.ClothingTestUtils.generateCsvFile;

public class ProductsServiceTest {

    @Spy
    private DasheryClothingCsvParser dasheryClothingCsvParser = new DasheryClothingCsvParser();
    @Spy
    private AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser = new AffiliateWindowClothingCsvParser();

    @InjectMocks
    private ProductsService productsService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testParsesCsvInDasheryFormat() throws Exception {
        List<Clothing> products = productsService.getClothingFrom(new Products(generateCsvFile("test.csv"), false));

        List<Clothing> expectedProducts = createClothing();
        assertThat(products, is(expectedProducts));
    }

    @Test
    public void testParsesCsvInAffiliateWindowFormat() throws IOException {
        List<Clothing> products = productsService.getClothingFrom(new Products(generateCsvFile("affiliatewindow.csv"), true));

        List<Clothing> expectedProducts = Lists.newArrayList(new Clothing("id123", "A Test Brand", "Test Item", 10000, "a_link.html", "image.jpg", Sets.newHashSet("A", "Tag", "Another")),
                new Clothing("id456", "Another Day", "Another Dollar", 200, "different_link", "image2.jpg", Sets.newHashSet("Different", "Tag")));
        assertThat(products, is(expectedProducts));
    }

    @Test(expected = RuntimeException.class)
    public void testGivesAnErrorWhenRequiredFieldsAreNotPresentInAffiliateWindowCsv() throws IOException {
        productsService.getClothingFrom(new Products(generateCsvFile("affiliatewindow-no-brand.csv"), true));
    }
}