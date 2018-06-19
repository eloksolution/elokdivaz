package in.eloksolutions.divaz.dtoclasses;

/**
 * Created by welcome on 2/24/2018.
 */

public class GalleryDTO {
    String id;
    String imagePath;
    String description;
    String category;
    String createDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "GalleryDTO{" +
                "id='" + id + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", description='" + description + '\'' +
                ", category='" + category + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
