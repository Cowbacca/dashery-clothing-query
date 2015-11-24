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
import uk.co.dashery.service.ClothingService;
import uk.co.dashery.service.ProductCsvParser;

import java.io.IOException;
import java.util.List;

@Controller
public class ProductsController {

    @Autowired
    private ProductCsvParser productCsvParser;
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
        List<Clothing> productBeans = productCsvParser.parseFromUrl(products.getUrl());
        clothingService.create(productBeans);
    }
}
