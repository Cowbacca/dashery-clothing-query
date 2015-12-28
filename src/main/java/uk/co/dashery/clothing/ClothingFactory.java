package uk.co.dashery.clothing;

import com.google.common.collect.Sets;
import org.springframework.stereotype.Component;
import uk.co.dashery.productfeed.Product;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClothingFactory {
    public List<Clothing> create(List<Product> products) {
        return products.stream()
                .map(this::createClothing)
                .collect(Collectors.toList());
    }

    private Clothing createClothing(Product product) {
        Clothing clothing = new Clothing();
        clothing.setId(product.getId());
        clothing.setBrand(product.getMerchant());
        clothing.setName(product.getName());
        clothing.setPrice(product.getPrice());
        clothing.setLink(product.getLink());
        clothing.setImageLink(product.getImageLink());
        clothing.setTags(getTags(product));
        return clothing;
    }

    private HashSet<String> getTags(Product product) {
        String[] tags = product.getDescription().split(" ");
        return Sets.newHashSet(tags);
    }
}
