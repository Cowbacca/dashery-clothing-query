package uk.co.dashery.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uk.co.dashery.data.Clothing;

import java.util.List;

@Service
public class TokenService {

    @Value("${dashery.autocomplete.url}")
    private String autocompleteURL;
    @Value("${dashery.autocomplete.creation.endpoint}")
    private String tokenCreationEndpoint;

    @Autowired
    private RestTemplate restTemplate;

    public void createFromClothing(List<Clothing> clothing) {
        restTemplate.postForEntity(autocompleteURL + tokenCreationEndpoint, clothing, Clothing.class);
    }
}
