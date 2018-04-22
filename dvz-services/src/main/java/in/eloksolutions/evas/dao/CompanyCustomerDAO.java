package in.eloksolutions.evas.dao;

import java.util.List;

import in.eloksolutions.evas.model.Company;
import in.eloksolutions.evas.model.CompanyCustomer;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Customer;

public interface CompanyCustomerDAO extends CommonDAO<CompanyCustomer>{
	public List<Customer> getCustomers(Context  ctx) ;
	public List<Company> getCompanies(Context  ctx,int customerId) ;
}
