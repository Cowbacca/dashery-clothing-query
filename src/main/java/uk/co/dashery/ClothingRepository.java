package uk.co.dashery;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClothingRepository extends MongoRepository<Clothing, String> {
    List<Clothing> findByName(@Param("name") String name);
}
