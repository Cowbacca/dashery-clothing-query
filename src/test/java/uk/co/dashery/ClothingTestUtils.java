package uk.co.dashery;

import com.google.common.collect.Sets;
import uk.co.dashery.data.Clothing;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClothingTestUtils {

    public static List<Clothing> createClothing() {
        List<Clothing> clothing = new ArrayList<>();
        clothing.add(generateClothing("Test Brand", "Test Item", 100, "A Tag", "Another Tag"));
        clothing.add(generateClothing("Another Day", "Another Dollar", 200, "Different Tag"));
        return clothing;
    }

    public static Clothing generateClothing(String brand, String name, int price, String... tags) {
        Clothing clothing = new Clothing();
        clothing.brand = brand;
        clothing.name = name;
        clothing.price = price;
        clothing.tags = Sets.newHashSet(tags);
        return clothing;
    }

    public static InputStream getTestCsvAsStream() {
        return ClothingTestUtils.class.getClassLoader().getResourceAsStream("test.csv");
    }
}
