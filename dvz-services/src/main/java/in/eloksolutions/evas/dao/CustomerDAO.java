package in.eloksolutions.evas.dao;

import in.eloksolutions.evas.model.Customer;

public interface CustomerDAO  extends CommonDAO<Customer>{
	public Integer updateDeviceToken(String  userid,String token) ;
}
