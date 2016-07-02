package models;

/**
 *
 * @author sergio
 */
public class Product {
    
    private Integer ref;
    private String name;
    private String description;
    private Boolean avaliable;
    private Float price;

    public Product(Integer ref, String name, String description, Boolean avaliable, Float price) {
        this.ref = ref;
        this.name = name;
        this.description = description;
        this.avaliable = avaliable;
        this.price = price;
    }

    public Product() {}

    public Integer getRef() {
        return ref;
    }

    public void setRef(Integer ref) {
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isAvaliable() {
        return avaliable;
    }

    public void setAvaliable(Boolean avaliable) {
        this.avaliable = avaliable;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    
}
