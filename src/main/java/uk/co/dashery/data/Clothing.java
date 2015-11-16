package uk.co.dashery.data;


import org.springframework.data.annotation.Id;

public class Clothing {

    @Id
    private String id;
    public String brand;
    public String name;
    public int price;
    public String link;
    public String type;
    public String colour;
    public String material;
    public String origin;

}
