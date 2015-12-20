package uk.co.dashery.products;

import org.junit.Test;

import java.io.IOException;
import java.io.Reader;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.dashery.ClothingTestUtils.generateCsvFile;

public class ProductsTest {

    @Test
    public void testGeneratesReaderFromURL() throws IOException {
        Products products = new Products(testCsvUrl());

        Reader reader = products.generateReader();

        assertThatFirstFiveCharsOfReaderAre(reader, "brand".toCharArray());
    }

    private String testCsvUrl() {
        return getClass().getClassLoader().getResource("test.csv").toString();
    }

    @Test
    public void testGeneratesReaderFromFile() throws IOException {
        Products products = new Products(generateCsvFile("test.csv"));

        Reader reader = products.generateReader();

        assertThatFirstFiveCharsOfReaderAre(reader, "brand".toCharArray());
    }

    private void assertThatFirstFiveCharsOfReaderAre(Reader reader, char[] firstFiveChars) throws IOException {
        char[] chars = new char[5];
        reader.read(chars);

        assertThat(chars, is(firstFiveChars));
    }
}
