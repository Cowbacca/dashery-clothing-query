package uk.co.dashery.clothing;

import org.springframework.stereotype.Service;
import uk.co.dashery.token.TokenService;

import javax.inject.Inject;
import java.util.List;

@Service
public class ClothingService {

    public static final String SEPARATOR = ",";
    @Inject
    private ClothingRepository clothingRepository;
    @Inject
    private TokenService tokenService;

    public List<Clothing> search(String search) {
        String[] tags = search.split(SEPARATOR);
        return clothingRepository.findByAllTagsIn(tags);
    }

    public void create(List<Clothing> clothing) {
        clothingRepository.save(clothing);
        tokenService.createFromClothing(clothing);
    }
}
