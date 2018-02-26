package com.eloksolutions.evas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.eloksolutions.evas.Application;
import com.eloksolutions.evas.model.Company;
import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Service;
import com.eloksolutions.evas.services.ServicesService;

@RestController
@RequestMapping("/services")
public class ServicesController {
	@Autowired
    private ServicesService servicesService;
	
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	  public Integer add(@RequestBody Service service, HttpServletRequest request) throws Exception {
		 System.out.println("Service "+service);
		 Company c=getCompany(request);
		  Integer id;
		try {
			id = servicesService.add(service,new Context(null, c.getSchema()));
		} catch (Exception e) {
			System.out.println("Error while saving Service "+service);
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
	  public Integer update(@RequestBody Service service, HttpServletRequest  request) throws Exception {
		 System.out.println("Service "+service+" id is "+service.getId());
		 Company c=getCompany(request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 System.out.println("CONTEXT IS"+ctx);
		  Integer id=null;
		try {
			id = servicesService.update(service,ctx);
		} catch (Exception e) {
			System.out.println("Error while updating Service "+service);
			e.printStackTrace();
		}
		  System.out.println("in insert id "+id);
		  return id;
	  }
	 
	 @RequestMapping(value = "/list/{offset}", method = RequestMethod.GET)
	  public  List<Service> findNext(@PathVariable("offset") Integer offset) {
		 System.out.println("findAll ");
		 List<Service> companies=servicesService.findNext(offset,null);
		  System.out.println("in insert companies "+companies);
		  return companies;
	  }
	 
	 @RequestMapping(value = "/getAll", method = RequestMethod.GET)
	  public  List<Service> findAll(HttpServletRequest request) throws Exception {
		 System.out.println("findAll ");
		 Company c=getCompany( request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 List<Service> companies=servicesService.findAll(ctx);
		  System.out.println("in insert companies "+companies);
		  return companies;
	  }
	 
	 @RequestMapping(value = "/getService/{ServiceId}", method = RequestMethod.GET)
	  public  Service getService(@PathVariable("ServiceId") Integer serviceId,HttpServletRequest request) throws Exception {
		 System.out.println("findAll ");
		 Company c=getCompany( request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		return servicesService.findById(serviceId, ctx);
	  }
}