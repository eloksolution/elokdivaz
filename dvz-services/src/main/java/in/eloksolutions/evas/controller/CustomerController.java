package in.eloksolutions.evas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.eloksolutions.evas.Application;
import in.eloksolutions.evas.model.Company;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Customer;
import in.eloksolutions.evas.services.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
    private CustomerService customerService;
	
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	  public Integer add(@RequestBody Customer customer) throws Exception {
		 System.out.println("Company "+customer);
		  Integer id;
		try {
			id = customerService.add(customer,null);
		} catch (Exception e) {
			System.out.println("Error while saving customer "+customer);
			e.printStackTrace();
			throw new Exception("Unable to save Customer, Please contact customer support. "+e.getMessage());
		}
		  System.out.println("BEW in insert id "+id);
		  return id;
	  }
	 
	 @RequestMapping(value = "/update", method = RequestMethod.POST)
	  public Integer update(@RequestBody Customer customer) throws Exception {
		 System.out.println("Company "+customer);
		  Integer id;
		try {
			id = customerService.update(customer,null);
		} catch (Exception e) {
			System.out.println("Error while saving customer "+customer);
			e.printStackTrace();
			throw new Exception("Unable to save Customer, Please contact customer support. "+e.getMessage());
		}
		  System.out.println("BEW in insert id "+id);
		  return id;
	  }
	 
	 @RequestMapping("/getCustomers")
	  public List<Customer> findAll(HttpServletRequest request) throws Exception {
		 Company c=getCompany(request);
		  List<Customer> result =customerService.findAll(new Context(null, c.getSchema()));
		  System.out.println("in find all result "+result);
		  return result;
	  }
	 
	 @RequestMapping(value = "/updateToken/{userid}/{token}", method = RequestMethod.POST)
	 public Integer updateDeviceToken(@PathVariable("userid") String userid,@PathVariable("token") String token,HttpServletRequest request) throws Exception {
		 System.out.println("Token is ************** "+token);
		 Company c=getCompany(request);
			return customerService.updateDeviceToken(userid, token);
	}
	 private Company getCompany( HttpServletRequest  request) throws Exception{
			String companyId=request.getHeader("companyId");
			if(companyId==null || companyId.trim().length()==0){
				throw new Exception("Invalid Request, Not Authorized");
			}
			 Company c=Application.getCompanyId(Integer.parseInt(companyId));
			if(c==null){
				throw new Exception("Invalid Request, Not Authorized");
			}
			 return c;
		}
	 
	 @RequestMapping(value = "/{custId}", method = RequestMethod.GET)
	  public  Customer getCustomer(@PathVariable("custId") Integer custId,HttpServletRequest request) throws Exception {
		 System.out.println("getCompany ");
		return customerService.findById(custId, null);
	  }
}
