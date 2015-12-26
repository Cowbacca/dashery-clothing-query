package uk.co.dashery;

import com.google.common.collect.Sets;
import org.springframework.mock.web.MockMultipartFile;
import uk.co.dashery.clothing.Clothing;

import java.io.IOException;
import java.io.InputStream;
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

    public static InputStream getTestCsvAsStream(String filename) {
        return ClothingTestUtils.class.getClassLoader().getResourceAsStream(filename);
    }

    public static MockMultipartFile generateCsvFile(String filename) throws IOException {
        InputStream inputFile = getTestCsvAsStream(filename);
        return new MockMultipartFile("csvFile", filename, "multipart/form-data", inputFile);
    }
}
