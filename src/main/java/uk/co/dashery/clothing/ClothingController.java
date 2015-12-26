package uk.co.dashery.clothing;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

@RestController
public class ClothingController {

    @Inject
    private ClothingService clothingService;

    @CrossOrigin
    @RequestMapping("/clothing")
    public List<Clothing> clothing(@RequestParam String search) {
        return clothingService.search(search);
    }
}
