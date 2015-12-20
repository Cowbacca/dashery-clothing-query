package uk.co.dashery.products;

import com.univocity.parsers.common.processor.RowListProcessor;
import org.springframework.stereotype.Component;
import uk.co.dashery.clothing.Clothing;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AffiliateWindowClothingCsvParser extends ClothingCsvParser<RowListProcessor> {

    @Override
    protected RowListProcessor getRowProcessor() {
        return new RowListProcessor();
    }

    @Override
    protected List<Clothing> getClothing(RowListProcessor rowProcessor) {
        String[] headers = rowProcessor.getHeaders();
        int priceColumn = 0;
        for (int i = 0; i < headers.length; i++) {
            switch (headers[i]) {
                case "search_price":
                    priceColumn = i;
                    break;
            }
        }
        final int definitivePriceColumn = priceColumn;

        return rowProcessor.getRows()
                .stream()
                .map(row -> {
                    Clothing clothing = new Clothing();
                    clothing.price = Integer.parseInt(row[definitivePriceColumn]);
                    clothing.tags = new HashSet<>();
                    return clothing;
                })
                .collect(Collectors.toList());
    }
}
