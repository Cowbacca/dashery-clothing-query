package uk.co.dashery;

import uk.co.dashery.data.Clothing;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ClothingTestUtils {

    public static List<Clothing> createClothing() {
        List<Clothing> clothing = new ArrayList<>();
        clothing.add(generateClothing("Test Brand", "Test Item", 100));
        clothing.add(generateClothing("Another Day", "Another Dollar", 200));
        return clothing;
    }

    private static Clothing generateClothing(String brand, String name, int price) {
        Clothing clothing = new Clothing();
        clothing.brand = brand;
        clothing.name = name;
        clothing.price = price;
        return clothing;
    }

    public static InputStream getTestCsvAsStream() {
        return ClothingTestUtils.class.getClassLoader().getResourceAsStream("test.csv");
    }
}
