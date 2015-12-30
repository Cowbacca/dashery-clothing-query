package uk.co.dashery.clothing;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uk.co.dashery.productfeed.ProductsCreatedEvent;

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

    @EventListener
    public void handleProductsCreated(ProductsCreatedEvent productsCreatedEvent) {
        List<Clothing> clothingList = clothingFactory.create(productsCreatedEvent.getProducts());
        clothingService.create(clothingList);
    }

    @RabbitListener(queues = "products")
    public void processNewClothing(List<Clothing> clothing) {
        clothingService.create(clothing);
    }
}
