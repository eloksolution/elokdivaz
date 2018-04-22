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
import in.eloksolutions.evas.model.Offer;
import in.eloksolutions.evas.services.OffersService;

@RestController
@RequestMapping("/offers")
public class OffersController {
	@Autowired
    private OffersService offersService;
	
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	  public Integer add(@RequestBody Offer offer, HttpServletRequest request) throws Exception {
		 System.out.println("OfferDao  Values"+offer);
		 Company c=getCompany(request);
		  Integer id;
		try {
			id = offersService.add(offer,new Context(null, c.getSchema()));
		} catch (Exception e) {
			System.out.println("Error while saving Service "+offer);
			e.printStackTrace();
			return 0;
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
	  public Integer update(@RequestBody Offer offfer, HttpServletRequest  request) throws Exception {
		 System.out.println("offfer "+offfer+" id is "+offfer.getId());
		 Company c=getCompany(request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 System.out.println("CONTEXT IS"+ctx);
		  Integer id=null;
		try {
			id = offersService.update(offfer,ctx);
		} catch (Exception e) {
			System.out.println("Error while updating Service "+offfer);
			e.printStackTrace();
		}
		  System.out.println("in insert id "+id);
		  return id;
	  }
	 
	 @RequestMapping(value = "/list/{offset}", method = RequestMethod.GET)
	  public  List<Offer> findNext(@PathVariable("offset") Integer offset) {
		 System.out.println("findAll ");
		 List<Offer> companies=offersService.findNext(offset,null);
		  System.out.println("in insert companies "+companies);
		  return companies;
	  }
	 
	 @RequestMapping(value = "/getAll", method = RequestMethod.GET)
	  public  List<Offer> findAll(HttpServletRequest request) throws Exception {
		 System.out.println("findAll ");
		 Company c=getCompany( request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 List<Offer> companies=offersService.findAll(ctx);
		  System.out.println("in insert companies "+companies);
		  return companies;
	  }
	 
	 @RequestMapping(value = "/getoffer/{offerId}", method = RequestMethod.GET)
	  public  Offer getService(@PathVariable("offerId") Integer offerId,HttpServletRequest request) throws Exception {
		 System.out.println("findAll ");
		 Company c=getCompany( request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		return offersService.findById(offerId, ctx);
	  }
}