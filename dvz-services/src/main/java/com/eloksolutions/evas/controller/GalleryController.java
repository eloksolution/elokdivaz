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
import com.eloksolutions.evas.model.Gallary;
import com.eloksolutions.evas.services.GalleryService;

@RestController
@RequestMapping("gallery")

public class GalleryController {
	@Autowired
	GalleryService galleryService;
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	 public Integer add(@RequestBody Gallary gallary, HttpServletRequest request) throws Exception{
		 System.out.println("messageDao  Values"+gallary);
		 
		 Company c=getCompany(request);
		  Integer id;
		try {	
			id = galleryService.add(gallary,new Context(null, c.getSchema()));
		} catch (Exception e) {
			System.out.println("Error while saving Service "+gallary);
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
	 @RequestMapping(value = "/getAll", method = RequestMethod.GET)
	  public  List<Gallary> findAll(HttpServletRequest request) throws Exception {
		 System.out.println("findAll ");
		 Company c=getCompany( request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 List<Gallary> galleys=galleryService.findAll(ctx);
		  System.out.println("in insert companies "+galleys);
		  return galleys;
	  }
	 
	 @RequestMapping(value = "/update", method = RequestMethod.POST)
	 public Integer update(@RequestBody Gallary gallary, HttpServletRequest  request) throws Exception {
	 	 System.out.println("offfer "+gallary+" id is "+gallary.getId());
	 	 Company c=getCompany(request);
	 	 System.out.println("Cache company is"+c);
	 	 Context ctx=new Context(null,c.getSchema());
	 	 System.out.println("CONTEXT IS"+ctx);
	 	  Integer id=null;
	 	try {
	 		id = galleryService.update(gallary,ctx);
	 	} catch (Exception e) {
	 		System.out.println("Error while updating Service "+gallary);
	 		e.printStackTrace();
	 	}
	 	  System.out.println("in insert id "+id);
	 	  return id;
	 }

	 @RequestMapping(value = "/list/{offset}", method = RequestMethod.GET)
	 public  List<Gallary> findNext(@PathVariable("offset") Integer offset) {
	 	 System.out.println("findAll ");
	 	 List<Gallary> gallarys=galleryService.findNext(offset,null);
	 	  System.out.println("in insert companies "+gallarys);
	 	  return gallarys;
	 }



	 @RequestMapping(value = "/getoffer/{galleryId}", method = RequestMethod.GET)
	 public  Gallary getService(@PathVariable("offerId") Integer galleryId,HttpServletRequest request) throws Exception {
	 	 System.out.println("findAll ");
	 	 Company c=getCompany( request);
	 	 System.out.println("Cache company is"+c);
	 	 Context ctx=new Context(null,c.getSchema());
	 	return galleryService.findById(galleryId, ctx);
	 }
	 
	

}
