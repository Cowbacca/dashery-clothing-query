package uk.co.dashery;

import uk.co.dashery.data.Clothing;

import java.util.ArrayList;
import java.util.List;

public class ClothingTestUtils {

    public static List<Clothing> createClothing() {
        ArrayList<Clothing> clothings = new ArrayList<>();
        Clothing clothing = new Clothing();
        clothing.colour = "grey";
        clothings.add(clothing);
        return clothings;
    }
}
