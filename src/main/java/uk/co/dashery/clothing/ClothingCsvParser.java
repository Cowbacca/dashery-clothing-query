package uk.co.dashery.clothing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uk.co.dashery.products.Products;

import java.io.IOException;
import java.util.List;

@Component
public class ClothingCsvParser {
    @Autowired
    private DasheryClothingCsvParser dasheryClothingCsvParser;

    public List<Clothing> parse(Products products) throws IOException {
        return dasheryClothingCsvParser.parse(products);
    }

}
