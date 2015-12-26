package uk.co.dashery.productfeed;

import uk.co.dashery.clothing.Clothing;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class ProductFeed {
    private final Reader reader;
    private final ClothingCsvParser clothingCsvParser;

    public ProductFeed(Reader reader, ClothingCsvParser clothingCsvParser) {
        this.reader = reader;
        this.clothingCsvParser = clothingCsvParser;
    }

    public List<Clothing> getClothing() throws IOException {
        return clothingCsvParser.parse(reader);
    }
}
