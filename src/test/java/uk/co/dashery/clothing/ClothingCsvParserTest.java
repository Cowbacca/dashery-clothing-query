package uk.co.dashery.clothing;

import org.junit.Before;
import org.junit.Test;
import uk.co.dashery.products.Products;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static uk.co.dashery.ClothingTestUtils.createClothing;
import static uk.co.dashery.ClothingTestUtils.generateCsvFile;

public class ClothingCsvParserTest {

    private ClothingCsvParser clothingCsvParser;

    @Before
    public void setUp() throws Exception {
        clothingCsvParser = new ClothingCsvParser();
    }

    @Test
    public void testParse() throws Exception {
        List<Clothing> products = clothingCsvParser.parse(new Products(generateCsvFile("test.csv")));

        List<Clothing> expectedProducts = createClothing();
        assertThat(products, is(expectedProducts));
    }
}