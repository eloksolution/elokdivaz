package in.eloksolutions.divaz.dataobjecs;

/**
 * Created by welcome on 3/1/2018.
 */

public class ServiceDataObject {
    String id;
    String name;
    String description;
    String imgePath;
    String price;
    String discount;
    String img_icon;

    public ServiceDataObject(String id, String name, String description, String imgePath, String price, String discount, String img_icon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imgePath = imgePath;
        this.price = price;
        this.discount = discount;
        this.img_icon = img_icon;
    }

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
        return imgePath;
    }

    public void setImgePath(String imgePath) {
        this.imgePath = imgePath;
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
                ", imgePath='" + imgePath + '\'' +
                ", price='" + price + '\'' +
                ", discount='" + discount + '\'' +
                ", img_icon='" + img_icon + '\'' +
                '}';
    }
}
