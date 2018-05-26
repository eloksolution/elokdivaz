package in.eloksolutions.divaz.dtoclasses;

import java.util.HashSet;

/**
 * Created by welcome on 2/24/2018.
 */

public class PackagesDTO {
    String id;
    String name;
    String description;
    String price;
    String category;
    String imagePath;
    HashSet<PackageItem> services=new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public HashSet<PackageItem> getServices() {
        return services;
    }

    public void setServices(HashSet<PackageItem> services) {
        this.services = services;
    }

    public void addServices(PackageItem item){
        services.add(item);

    }
    public void removeServices(PackageItem item){
        services.remove(item);
    }


    @Override
    public String toString() {
        return "PackagesDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price='" + price + '\'' +
                ", category='" + category + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }



}
