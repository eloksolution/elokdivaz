package in.eloksolutions.beaty.activities.dtoclasses;

/**
 * Created by welcome on 2/24/2018.
 */

public class ServiceDTO {
    String id;
    String name;
    String description;
    String imagePath;
    String price;
    String discount;
    String img_icon;

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

    public String getImgePath() {
        return imagePath;
    }

    public void setImgePath(String imgePath) {
        this.imagePath = imgePath;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getImg_icon() {
        return img_icon;
    }

    public void setImg_icon(String img_icon) {
        this.img_icon = img_icon;
    }

    @Override
    public String toString() {
        return "ServiceDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", img_icon='" + img_icon + '\'' +
                '}';
    }
}
