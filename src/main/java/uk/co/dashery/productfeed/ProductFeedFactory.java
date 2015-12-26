package uk.co.dashery.productfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductFeedFactory {

    @Autowired
    private DasheryClothingCsvParser dasheryClothingCsvParser;
    @Autowired
    private AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser;

    public ProductFeed create(ProductFeedForm productFeedForm) throws IOException {
        return new ProductFeed(productFeedForm.generateReader(), getClothingCsvParser(productFeedForm));
    }

    private ClothingCsvParser getClothingCsvParser(ProductFeedForm productFeedForm) {
        ClothingCsvParser clothingCsvParser;
        if (productFeedForm.isAffiliateWindowFormat()) {
            clothingCsvParser = affiliateWindowClothingCsvParser;
        } else {
            clothingCsvParser = dasheryClothingCsvParser;
        }
        return clothingCsvParser;
    }
}
