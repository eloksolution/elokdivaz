package in.eloksolutions.divaz.dtoclasses;

/**
 * Created by welcome on 2/24/2018.
 */

public class MessageDTO {
    String id;
    String subject;
    String description;
    String img_path;
    String createDate;
    String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return img_path;
    }

    public void setImgPath(String imgPath) {
        this.img_path = imgPath;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "MessageDTO{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", description='" + description + '\'' +
                ", img_path='" + img_path + '\'' +
                ", createDate='" + createDate + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
