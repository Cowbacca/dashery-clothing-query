package uk.co.dashery.clothing;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Clothing {

    @Id
    @JsonIgnore
    private String id;
    private String brand;
    private String name;
    private int price;
    private String link;
    private String imageLink;
    private Set<String> tags;

    public Clothing(String id) {
        this(id, null, null, 0, null, null, null);
    }
}
