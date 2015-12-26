package uk.co.dashery.productfeed;

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

public class ProductFeedTest {

    @Spy
    private DasheryClothingCsvParser dasheryClothingCsvParser = new DasheryClothingCsvParser();
    @Spy
    private AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser = new
            AffiliateWindowClothingCsvParser();

    @InjectMocks
    private ProductFeedFactory productFeedFactory;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void testParsesCsvInDasheryFormat() throws Exception {
        ProductFeed productFeed = productFeedFactory.create(new ProductFeedForm(generateCsvFile
                ("test.csv"), false));

        List<Clothing> clothing = productFeed.getClothing();

        List<Clothing> expectedClothing = createClothing();
        assertThat(clothing, is(expectedClothing));
    }

    @Test
    public void testParsesCsvInAffiliateWindowFormat() throws IOException {
        ProductFeed productFeed = productFeedFactory.create(new ProductFeedForm(generateCsvFile
                ("affiliatewindow.csv"),
                true));

        List<Clothing> clothing = productFeed.getClothing();

        List<Clothing> expectedClothing = Lists.newArrayList(
                new Clothing("id123", "A Test Brand", "Test Item", 10000, "a_link.html", "image" +
                        ".jpg",
                        Sets.newHashSet("A", "Tag", "Another")),
                new Clothing("id456", "Another Day", "Another Dollar", 200, "different_link",
                        "image2.jpg",
                        Sets.newHashSet("Different", "Tag")));
        assertThat(clothing, is(expectedClothing));
    }

    @Test(expected = CsvFormatException.class)
    public void testGivesAnErrorWhenRequiredFieldsAreNotPresentInAffiliateWindowCsv() throws
            IOException {
        ProductFeed productFeed = productFeedFactory.create(
                new ProductFeedForm(generateCsvFile("affiliatewindow-no-brand.csv"), true));

        List<Clothing> clothing = productFeed.getClothing();
    }
}