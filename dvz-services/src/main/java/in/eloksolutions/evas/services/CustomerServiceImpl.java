package in.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.dao.CustomerDAO;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Customer;

@Repository("customerService")
public class CustomerServiceImpl implements CustomerService{
	@Autowired
    private CustomerDAO customerDAO;
	
	@Override
	public Integer add(Customer customer, Context ctx) throws Exception {
		return customerDAO.add(customer, ctx);
	}
	@Override
	public List<Customer> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Customer> findAll(Context ctx) {
		return customerDAO.findAll(ctx);
	}
	@Override
	public Customer findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer update(Customer model, Context ctx) throws Exception {
		return null;
	}
	@Override
	public Integer updateDeviceToken(String userid, String token) throws Exception {
		try {
			Integer i= customerDAO.updateDeviceToken(userid, token);
			return i;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error while updating device token ");
		}
	}
	
}
