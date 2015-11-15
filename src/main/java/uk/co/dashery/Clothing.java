package uk.co.dashery;


import org.springframework.data.annotation.Id;

public class Clothing {

    @Id
    private String id;
    public String brand;
    public String name;
    public String price;
    public String type;
    public String colour;
    public String material;
    public String origin;

}
