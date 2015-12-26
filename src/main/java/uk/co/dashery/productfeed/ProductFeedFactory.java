package uk.co.dashery.productfeed;

import org.springframework.stereotype.Component;
import uk.co.dashery.productfeed.csv.AffiliateWindowClothingCsvParser;
import uk.co.dashery.productfeed.csv.ClothingCsvParser;
import uk.co.dashery.productfeed.csv.DasheryClothingCsvParser;

import javax.inject.Inject;
import java.io.IOException;

@Component
public class ProductFeedFactory {

    @Inject
    private DasheryClothingCsvParser dasheryClothingCsvParser;
    @Inject
    private AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser;

    public ProductFeed create(ProductFeedForm productFeedForm) throws IOException {
        return new ProductFeed(productFeedForm.generateReader(), getClothingCsvParser
                (productFeedForm));
    }

    private ClothingCsvParser getClothingCsvParser(ProductFeedForm productFeedForm) {
        if (productFeedForm.isAffiliateWindowFormat()) {
            return affiliateWindowClothingCsvParser;
        } else {
            return dasheryClothingCsvParser;
        }
    }
}
