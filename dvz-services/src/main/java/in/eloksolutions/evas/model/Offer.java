package in.eloksolutions.evas.model;

import java.sql.Date;

/**
 * Created by welcome on 2/23/2018.
 */

public class Offer {
    int id;
    String name;
    String description;
    int offerPrice;
    int beforeOfferPrice;
    String imgePath;
    String sStartDate;
    String sEndDate;
    Date startDate;
    Date endDate;
    Date createdDate;
    String sCreatedDate;

    
    public Offer() {
		
	}

	public Offer(int id, String name, String description, int offerPrice, int beforeOfferPrice,
			String imgePath, Date startDate, Date endDate, Date createdDate) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.offerPrice = offerPrice;
		this.beforeOfferPrice = beforeOfferPrice;
		this.imgePath = imgePath;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdDate = createdDate;
	}

	
	public Offer(int id, String name, String description, int offerPrice, int beforeOfferPrice, String imgePath, String startDate,
			String endDate, String createdDate) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.offerPrice = offerPrice;
		this.beforeOfferPrice = beforeOfferPrice;
		this.sStartDate = startDate;
		this.imgePath = imgePath;
		this.sEndDate = endDate;
		this.sCreatedDate = createdDate;
	}

	
	public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public int getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(int offerPrice) {
        this.offerPrice = offerPrice;
    }

    public int getBeforeOfferPrice() {
        return beforeOfferPrice;
    }

    public void setBeforeOfferPrice(int beforeOfferPrice) {
        this.beforeOfferPrice = beforeOfferPrice;
    }

    public String getImgePath() {
        return imgePath;
    }

    public void setImgePath(String imgePath) {
        this.imgePath = imgePath;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    

    public String getsStartDate() {
		return sStartDate;
	}

	public void setsStartDate(String sStartDate) {
		this.sStartDate = sStartDate;
	}

	public String getsEndDate() {
		return sEndDate;
	}

	public void setsEndDate(String sEndDate) {
		this.sEndDate = sEndDate;
	}
	
	

	public String getsCreatedDate() {
		return sCreatedDate;
	}

	public void setsCreatedDate(String sCreatedDate) {
		this.sCreatedDate = sCreatedDate;
	}

	@Override
	public String toString() {
		return "Offer [id=" + id + ", name=" + name + ", description=" + description + ", offerPrice=" + offerPrice
				+ ", beforeOfferPrice=" + beforeOfferPrice + ", imgePath=" + imgePath + ", sStartDate=" + sStartDate
				+ ", sEndDate=" + sEndDate + ", startDate=" + startDate + ", endDate=" + endDate + ", createdDate="
				+ createdDate + ",sCreatedDate=" + sCreatedDate + "]";
	}

	

	
}
