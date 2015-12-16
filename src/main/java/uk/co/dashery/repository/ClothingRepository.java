package uk.co.dashery.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import uk.co.dashery.data.Clothing;

public interface ClothingRepository extends MongoRepository<Clothing, String>, ClothingRepositoryCustom {
}
