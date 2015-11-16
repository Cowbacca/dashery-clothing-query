package uk.co.dashery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.dashery.data.Clothing;
import uk.co.dashery.service.ClothingService;

import java.util.List;

@RestController
public class ClothingController {

    @Autowired
    private ClothingService clothingService;

    @RequestMapping("/clothing")
    public List<Clothing> clothing(@RequestParam String search) {
        return clothingService.search(search);
    }
}
