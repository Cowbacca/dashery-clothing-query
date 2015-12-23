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

    private final Map<String, BiConsumer<Clothing, String>> nameToConversionMap;

    AffiliateWindowClothingCsvParser() {
        nameToConversionMap = new HashMap<>();
        nameToConversionMap.put("search_price", (clothing, price) -> clothing.setPrice(Integer.parseInt(price.replace(".", ""))));
        nameToConversionMap.put("description", (clothing, description) -> clothing.setTags(Sets.newHashSet(description.split(" "))));
        nameToConversionMap.put("merchant_name", (clothing, brand) -> clothing.setBrand(brand));
        nameToConversionMap.put("product_name", (clothing, name) -> clothing.setName(name));
        nameToConversionMap.put("aw_deep_link", (clothing, link) -> clothing.setLink(link));
        nameToConversionMap.put("merchant_image_url", (clothing, imageLink) -> clothing.setImageLink(imageLink));
        nameToConversionMap.put("merchant_product_id", (clothing, id) -> clothing.setId(id));
    }

    @Override
    protected List<Clothing> getClothing(RowListProcessor rowProcessor) {
        Map<String, Integer> nameToIndexMap = generateNameToIndexMap(rowProcessor);

        return generateClothingFromRows(rowProcessor, nameToIndexMap);
    }

    private Map<String, Integer> generateNameToIndexMap(RowListProcessor rowProcessor) {
        String[] headers = rowProcessor.getHeaders();
        return IntStream.range(0, headers.length)
                .boxed()
                .collect(Collectors.toMap(i -> headers[i], i -> i));
    }

    private List<Clothing> generateClothingFromRows(RowListProcessor rowProcessor, Map<String, Integer> nameToIndexMap) {
        return rowProcessor.getRows()
                .stream()
                .map(row -> {
                    Clothing clothing = new Clothing();
                    nameToConversionMap.forEach((name, conversion) -> {
                        Integer index = getIndex(nameToIndexMap, name);
                        conversion.accept(clothing, row[index]);
                    });
                    return clothing;
                })
                .collect(Collectors.toList());
    }

    private Integer getIndex(Map<String, Integer> nameToIndexMap, String name) {
        Integer index = nameToIndexMap.get(name);
        if (index == null) {
            throw new RuntimeException("Column " + name + " must be present in CSV file.");
        }
        return index;
    }
}
