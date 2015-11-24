package uk.co.dashery.service;

import org.junit.Before;
import org.junit.Test;
import uk.co.dashery.data.Clothing;

import java.net.URL;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static uk.co.dashery.ClothingTestUtils.createClothing;

public class ProductCsvParserTest {

    private ProductCsvParser productCsvParser;

    @Before
    public void setUp() throws Exception {
        productCsvParser = new ProductCsvParser();
    }

    @Test
    public void testParseFromUrl() throws Exception {
        URL resource = getClass().getClassLoader().getResource("test.csv");

        List<Clothing> products = productCsvParser.parseFromUrl(resource.toString());

        List<Clothing> expectedProducts = createClothing();
        assertThat(products, is(expectedProducts));
    }
}