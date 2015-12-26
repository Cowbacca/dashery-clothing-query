package uk.co.dashery.productfeed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.co.dashery.clothing.Clothing;
import uk.co.dashery.clothing.ClothingService;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductFeedController {

    @Autowired
    private ProductFeedFactory productFeedFactory;
    @Autowired
    private ClothingService clothingService;

    @RequestMapping(value = "/productFeed", method = RequestMethod.GET)
    public String productsForm(Model model) {
        model.addAttribute("productFeed", new ProductFeedForm());
        return "productFeed";
    }

    @RequestMapping(value = "/productFeed", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void ingestProducts(@ModelAttribute ProductFeedForm productFeedForm) throws IOException {
        ProductFeed productFeed = productFeedFactory.create(productFeedForm);
        List<Clothing> clothing = productFeed.getClothing();
        clothingService.create(clothing);
    }
}
