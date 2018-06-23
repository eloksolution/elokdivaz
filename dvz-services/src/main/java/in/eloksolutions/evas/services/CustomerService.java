package in.eloksolutions.evas.services;

import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Customer;

public interface CustomerService extends CommonService<Customer>{
	 public Integer updateDeviceToken(String userid, String token) throws Exception;
	 public Integer updateMyRecentParlours(Integer  userid,Integer parlourId,String parlourName, Context ctx) ;
}
