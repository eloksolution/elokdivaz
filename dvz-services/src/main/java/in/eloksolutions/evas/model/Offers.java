package in.eloksolutions.evas.model;

/**
 * Created by welcome on 2/23/2018.
 */

public class Offers {
    String id;
    String name;
    String description;
    String imgePath;
   
    public Offers(){
    	
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

   

    @Override
    public String toString() {
        return "OfferDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
               
                '}';
    }
}
