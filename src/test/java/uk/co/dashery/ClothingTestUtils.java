package uk.co.dashery;

import com.google.common.collect.Sets;
import uk.co.dashery.clothing.Clothing;

import java.util.ArrayList;
import java.util.List;

public class ClothingTestUtils {

    public static List<Clothing> createClothing() {
        List<Clothing> clothing = new ArrayList<>();
        clothing.add(generateClothing("id123", "Test Brand", "Test Item", 100, "A Tag", "Another " +
                "Tag"));
        clothing.add(generateClothing("id456", "Another Day", "Another Dollar", 200, "Different " +
                "Tag"));
        return clothing;
    }

    public static Clothing generateClothing(String id, String brand, String name, int price,
                                            String... tags) {
        Clothing clothing = new Clothing(id);
        clothing.setBrand(brand);
        clothing.setName(name);
        clothing.setPrice(price);
        clothing.setTags(Sets.newHashSet(tags));
        return clothing;
    }

}
