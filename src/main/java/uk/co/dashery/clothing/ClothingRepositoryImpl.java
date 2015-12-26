package uk.co.dashery.clothing;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.inject.Inject;
import java.util.List;

public class ClothingRepositoryImpl implements ClothingRepositoryCustom {
    @Inject
    private MongoOperations mongoOperations;

    @Override
    public List<Clothing> findByAllTagsIn(String... tags) {
        Criteria whereAllOfTheGivenTags = new Criteria("tags").all(tags);
        Query query = new Query(whereAllOfTheGivenTags);
        return mongoOperations.find(query, Clothing.class);
    }
}
