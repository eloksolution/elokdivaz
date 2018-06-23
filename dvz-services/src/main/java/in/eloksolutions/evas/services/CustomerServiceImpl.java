package in.eloksolutions.evas.services;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.dao.CustomerDAO;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Customer;
import in.eloksolutions.evas.util.ParlourFormattor;
import in.eloksolutions.evas.vo.Parlour;

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
		return customerDAO.findById(id, ctx);
	}
	@Override
	public Integer update(Customer model, Context ctx) throws Exception {
		return customerDAO.update(model, ctx);
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
	@Override
	public Integer updateMyRecentParlours(Integer userid, Integer parlourId, String parlourName, Context ctx) {
		Customer cust=customerDAO.findById(userid, ctx);
		Set<Parlour> myParlours=cust.getMyParalours();
		if(myParlours==null){
			myParlours=new TreeSet<>();
		}
		myParlours.add(new Parlour(parlourName,parlourId,new Date()));
		String myRecentParlours;
		try {
			myRecentParlours = ParlourFormattor.format(myParlours);
			
			customerDAO.updateMyRecentParlours(userid, myRecentParlours);
		} catch (Exception e) {
			System.out.println("Error while updating parlours "+parlourId);
			e.printStackTrace();
		}
		return 0;
	}
	
}
