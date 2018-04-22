package in.eloksolutions.evas.model;

public class CompanyCustomer {
	int id;
	int customerId;
	String customerName;
	int companyId;
	String companyName;
	
	public CompanyCustomer() {
		super();
	}
	public CompanyCustomer(int id, int customerId, String customerName, int companyId, String companyName) {
		super();
		this.id = id;
		this.customerId = customerId;
		this.customerName = customerName;
		this.companyId = companyId;
		this.companyName = companyName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
}
