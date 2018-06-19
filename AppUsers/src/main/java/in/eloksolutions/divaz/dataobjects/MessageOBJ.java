package in.eloksolutions.divaz.dataobjects;

/**
 * Created by welcome on 2/24/2018.
 */

public class MessageOBJ {
    String id;
    String subject;
    String description;
    String imgPath;
    String createDate;
    String type;

    public MessageOBJ(String id, String subject, String description, String imgPath, String createDate, String type) {
        this.id = id;
        this.subject = subject;
        this.description = description;
        this.imgPath = imgPath;
        this.createDate = createDate;
        this.type = type;
    }

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
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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
                ", imgPath='" + imgPath + '\'' +
                ", createDate='" + createDate + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
