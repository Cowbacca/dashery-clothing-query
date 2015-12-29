package uk.co.dashery.clothing;

import org.springframework.web.bind.annotation.*;
import uk.co.dashery.productfeed.Product;

import javax.inject.Inject;
import java.util.List;

@RestController("/clothing")
public class ClothingController {

    @Inject
    private ClothingService clothingService;
    @Inject
    private ClothingFactory clothingFactory;

    @CrossOrigin
    @RequestMapping
    public List<Clothing> clothing(@RequestParam String search) {
        return clothingService.search(search);
    }

    @RequestMapping(value = "/fromProducts", method = RequestMethod.PUT)
    public void createFrom(List<Product> products) {
        List<Clothing> clothingList = clothingFactory.create(products);
        clothingService.create(clothingList);
    }
}
