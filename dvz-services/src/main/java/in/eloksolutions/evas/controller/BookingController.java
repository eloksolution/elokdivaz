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
import in.eloksolutions.evas.model.Booking;
import in.eloksolutions.evas.model.Company;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.services.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	@Autowired
    private BookingService bokingService;
	
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	  public Integer add(@RequestBody Booking booking, HttpServletRequest request) throws Exception {
		 System.out.println("Booking "+booking);
		 Company c=getCompany(request);
		  Integer id;
		try {
			id = bokingService.add(booking,new Context(null, c.getSchema(), c.getId(), c.getName()));
		} catch (Exception e) {
			System.out.println("Error while saving Booking "+booking);
			e.printStackTrace();
			throw new Exception("Unable to Insert Booking "+e.getMessage());
		}
		  System.out.println("in insert id "+id);
		  return id;
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
	 
	 @RequestMapping(value = "/update", method = RequestMethod.POST)
	  public Integer update(@RequestBody Booking booking, HttpServletRequest  request) throws Exception {
		 System.out.println("Booking "+booking+" id is "+booking.getCompanyId());
		 Company c=getCompany(request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 System.out.println("CONTEXT IS"+ctx);
		  Integer id=null;
		try {
			id = bokingService.update(booking,ctx);
		} catch (Exception e) {
			System.out.println("Error while updating booking "+booking);
			e.printStackTrace();
			throw new Exception("Unable to update Booking "+e.getMessage());
		}
		  System.out.println("in insert id "+id);
		  return id;
	  }
	 
	 @RequestMapping(value = "/list/{offset}", method = RequestMethod.GET)
	  public  List<Booking> findNext(@PathVariable("offset") Integer offset) {
		 System.out.println("findAll ");
		 List<Booking> companies=bokingService.findNext(offset,null);
		  System.out.println("in insert companies "+companies);
		  return companies;
	  }
	 
	 @RequestMapping(value = "/getAll", method = RequestMethod.GET)
	  public  List<Booking> findAll(HttpServletRequest request) throws Exception {
		 System.out.println("findAll ");
		 Company c=getCompany( request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 List<Booking> companies=bokingService.findAll(ctx);
		  System.out.println("in insert companies "+companies);
		  return companies;
	  }
	 
	 @RequestMapping(value = "/getBooking/{bookingId}", method = RequestMethod.GET)
	  public  Booking getBooking(@PathVariable("bookingId") Integer bookingId,HttpServletRequest request) throws Exception {
		 System.out.println("findAll ");
		 Company c=getCompany( request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		return bokingService.findById(bookingId, ctx);
	  }
}