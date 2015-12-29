package uk.co.dashery.productfeed;


import com.google.common.collect.Lists;

import java.util.List;

public class ProductFeedUtils {
    public static List<Product> expectedProducts() {
        return Lists.newArrayList(
                new Product("id123", "A Test Brand", "Test Item", "Some description or other.",
                        10000, "a_link.html", "image.jpg"),
                new Product("id456", "Another Day", "Another Dollar", "A different description.",
                        200, "different_link",
                        "image2.jpg"));
    }

}
