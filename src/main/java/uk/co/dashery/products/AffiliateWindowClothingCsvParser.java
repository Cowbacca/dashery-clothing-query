package uk.co.dashery.products;

import com.google.common.collect.Sets;
import com.univocity.parsers.common.processor.RowListProcessor;
import org.springframework.stereotype.Component;
import uk.co.dashery.clothing.Clothing;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class AffiliateWindowClothingCsvParser extends ClothingCsvParser<RowListProcessor> {

    @Override
    protected RowListProcessor getRowProcessor() {
        return new RowListProcessor();
    }

    private final Map<String, BiConsumer<Clothing, String>> columnNameMap;

    AffiliateWindowClothingCsvParser() {
        columnNameMap = new HashMap<>();
        columnNameMap.put("search_price", (clothing, price) -> clothing.setPrice(Integer.parseInt(price.replace(".", ""))));
        columnNameMap.put("description", (clothing, description) -> clothing.setTags(Sets.newHashSet(description.split(" "))));
        columnNameMap.put("merchant_name", (clothing, brand) -> clothing.setBrand(brand));
        columnNameMap.put("product_name", (clothing, name) -> clothing.setName(name));
        columnNameMap.put("aw_deep_link", (clothing, link) -> clothing.setLink(link));
        columnNameMap.put("merchant_image_url", (clothing, imageLink) -> clothing.setImageLink(imageLink));
        columnNameMap.put("merchant_product_id", (clothing, id) -> clothing.setId(id));
    }

    @Override
    protected List<Clothing> getClothing(RowListProcessor rowProcessor) {
        Map<Integer, BiConsumer<Clothing, String>> columnIndexMap = generateColumnIndexMap(rowProcessor);

        return generateClothingFromRows(rowProcessor, columnIndexMap);
    }

    private Map<Integer, BiConsumer<Clothing, String>> generateColumnIndexMap(RowListProcessor rowProcessor) {
        String[] headers = rowProcessor.getHeaders();
        return IntStream.range(0, headers.length)
                .boxed()
                .filter(i -> columnNameMap.get(headers[i]) != null)
                .collect(Collectors.toMap(i -> i, i -> columnNameMap.get(headers[i])));
    }

    private List<Clothing> generateClothingFromRows(RowListProcessor rowProcessor, Map<Integer, BiConsumer<Clothing, String>> columnIndexMap) {
        return rowProcessor.getRows()
                .stream()
                .map(row -> {
                    Clothing clothing = new Clothing();
                    columnIndexMap.forEach((index, mapping) -> mapping.accept(clothing, row[index]));
                    return clothing;
                })
                .collect(Collectors.toList());
    }
}
