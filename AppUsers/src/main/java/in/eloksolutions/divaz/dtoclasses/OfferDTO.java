package in.eloksolutions.divaz.dtoclasses;

/**
 * Created by welcome on 2/23/2018.
 */

public class OfferDTO {
    String id;
    String name;
    String description;
    String offerPrice;
    String beforeOfferPrice;
    String imgePath;
    String startDate;
    String endDate;
    String createdDate;

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

    public String getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(String offerPrice) {
        this.offerPrice = offerPrice;
    }

    public String getBeforeOfferPrice() {
        return beforeOfferPrice;
    }

    public void setBeforeOfferPrice(String beforeOfferPrice) {
        this.beforeOfferPrice = beforeOfferPrice;
    }

    public String getImgePath() {
        return imgePath;
    }

    public void setImgePath(String imgePath) {
        this.imgePath = imgePath;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "OfferDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", offerPrice='" + offerPrice + '\'' +
                ", beforeOfferPrice='" + beforeOfferPrice + '\'' +
                ", imgePath='" + imgePath + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", createdDate='" + createdDate + '\'' +
                '}';
    }
}
