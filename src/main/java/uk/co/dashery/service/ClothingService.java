package uk.co.dashery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.dashery.data.Clothing;
import uk.co.dashery.repository.ClothingRepository;

import java.util.List;

@Service
public class ClothingService {

    public static final String SEPARATOR = ",";
    @Autowired
    private ClothingRepository clothingRepository;
    @Autowired
    private TokenService tokenService;

    public List<Clothing> search(String search) {
        String[] tags = search.split(SEPARATOR);
        return clothingRepository.findByAllTagsIn(tags);
    }

    public void create(List<Clothing> clothing) {
        clothingRepository.insert(clothing);
        tokenService.createFromClothing(clothing);
    }
}
