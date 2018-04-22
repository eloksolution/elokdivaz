package in.eloksolutions.evas.model;

public class Context {
	Integer userId;
	String schema;
	Integer companyId;
	String companyName;
	
	public Context(Integer userId, String schema) {
		super();
		this.userId = userId;
		this.schema = schema;
	}
	
	public Context(Integer userId, String schema, Integer companyId, String companyName) {
		super();
		this.userId = userId;
		this.schema = schema;
		this.companyId = companyId;
		this.companyName = companyName;
	}

	public Integer getUserId() {
		return userId;
	}
	public String getSchema() {
		return schema;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	@Override
	public String toString() {
		return "Context [userId=" + userId + ", schema=" + schema + ", companyId=" + companyId + ", companyName="
				+ companyName + "]";
	}
	
}
