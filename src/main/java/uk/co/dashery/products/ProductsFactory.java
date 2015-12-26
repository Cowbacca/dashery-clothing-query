package uk.co.dashery.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ProductsFactory {

    @Autowired
    private DasheryClothingCsvParser dasheryClothingCsvParser;
    @Autowired
    private AffiliateWindowClothingCsvParser affiliateWindowClothingCsvParser;

    public Products create(ProductsForm productsForm) throws IOException {
        return new Products(productsForm.generateReader(), productsForm.isAffiliateWindowFormat(),
                dasheryClothingCsvParser, affiliateWindowClothingCsvParser);
    }
}
