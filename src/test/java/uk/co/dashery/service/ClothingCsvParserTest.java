package uk.co.dashery.service;

import org.junit.Before;
import org.junit.Test;
import uk.co.dashery.data.Clothing;

import java.io.InputStreamReader;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static uk.co.dashery.ClothingTestUtils.createClothing;
import static uk.co.dashery.ClothingTestUtils.getTestCsvAsStream;

public class ClothingCsvParserTest {

    private ClothingCsvParser clothingCsvParser;

    @Before
    public void setUp() throws Exception {
        clothingCsvParser = new ClothingCsvParser();
    }

    @Test
    public void testParse() throws Exception {
        InputStreamReader resource = new InputStreamReader(getTestCsvAsStream());

        List<Clothing> products = clothingCsvParser.parse(resource);

        List<Clothing> expectedProducts = createClothing();
        assertThat(products, is(expectedProducts));
    }
}