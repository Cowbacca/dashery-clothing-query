package uk.co.dashery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.dashery.data.Clothing;
import uk.co.dashery.repository.ClothingRepository;

import java.util.List;

@Service
public class ClothingService {

    @Autowired
    private ClothingRepository clothingRepository;
    @Autowired
    private QueryGenerator queryGenerator;

    public List<Clothing> search(String search) {
        return clothingRepository.findByQuery(queryGenerator.generate(search));
    }

    public void create(List<Clothing> clothing) {
        clothingRepository.insert(clothing);
    }
}
