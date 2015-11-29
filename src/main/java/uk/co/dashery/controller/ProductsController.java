package uk.co.dashery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import uk.co.dashery.data.Clothing;
import uk.co.dashery.data.Products;
import uk.co.dashery.service.ClothingCsvParser;
import uk.co.dashery.service.ClothingService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ClothingCsvParser clothingCsvParser;
    @Autowired
    private ClothingService clothingService;

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String ingestUrlForm(Model model) {
        model.addAttribute("products", new Products());
        return "products";
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public void ingestProductsFromUrl(@ModelAttribute Products products) throws IOException {
        List<Clothing> clothing = clothingCsvParser.parse(generateReader(products.getUrl()));
        clothingService.create(clothing);
    }

    private Reader generateReader(String urlString) throws IOException {
        URL url = new URL(urlString);
        return new BufferedReader(new InputStreamReader(url.openStream()));
    }
}
