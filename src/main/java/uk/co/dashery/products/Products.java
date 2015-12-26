package uk.co.dashery.products;

import uk.co.dashery.clothing.Clothing;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

public class Products {
    private final Reader reader;
    private final boolean isAffiliateWindowFormat;
    private DasheryClothingCsvParser dasheryClothingCsvParser;
    private AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser;

    public Products(Reader reader, boolean isAffiliateWindowFormat, DasheryClothingCsvParser dasheryClothingCsvParser,
                    AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser) {
        this.reader = reader;
        this.isAffiliateWindowFormat = isAffiliateWindowFormat;
        this.dasheryClothingCsvParser = dasheryClothingCsvParser;
        this.affiliateWindowClothingCsvParser = affiliateWindowClothingCsvParser;
    }

    public List<Clothing> getClothing() throws IOException {
        if (isAffiliateWindowFormat) {
            return affiliateWindowClothingCsvParser.parse(reader);
        } else {
            return dasheryClothingCsvParser.parse(reader);
        }
    }
}
