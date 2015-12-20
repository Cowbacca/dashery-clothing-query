package uk.co.dashery.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.co.dashery.clothing.Clothing;
import uk.co.dashery.clothing.ClothingCsvParser;
import uk.co.dashery.clothing.ClothingService;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ClothingCsvParser clothingCsvParser;
    @Autowired
    private ClothingService clothingService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String productsForm(Model model) {
        model.addAttribute("products", new Products());
        return "products";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void ingestProducts(@ModelAttribute Products products) throws IOException {
        Reader csvReader = products.generateReader();
        List<Clothing> clothing = clothingCsvParser.parse(csvReader);
        clothingService.create(clothing);
    }
}
