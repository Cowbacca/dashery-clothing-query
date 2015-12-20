package uk.co.dashery.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.dashery.clothing.Clothing;

import java.io.IOException;
import java.util.List;

@Component
public class ProductsService {
    @Autowired
    private DasheryClothingCsvParser dasheryClothingCsvParser;
    @Autowired
    private AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser;

    public List<Clothing> getClothingFrom(Products products) throws IOException {
        if (products.isAffiliateWindowFormat()) {
            return affiliateWindowClothingCsvParser.parse(products);
        } else {
            return dasheryClothingCsvParser.parse(products);
        }
    }

}
