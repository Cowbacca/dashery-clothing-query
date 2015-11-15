package uk.co.dashery;


import org.springframework.data.annotation.Id;

public class Clothing {

    @Id
    private String id;
    public String name;
    public String colour;
}
