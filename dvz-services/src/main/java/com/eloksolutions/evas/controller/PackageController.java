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
import com.eloksolutions.evas.model.Offer;
import com.eloksolutions.evas.model.Packages;
import com.eloksolutions.evas.services.PackageService;

@RestController
@RequestMapping("packages")
public class PackageController {
	@Autowired
	PackageService packageService;
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	 public Integer add(@RequestBody Packages packages, HttpServletRequest request) throws Exception{
		 System.out.println("messageDao  Values"+packages);
		 
		 Company c=getCompany(request);
		  Integer id;
		try {	
			id = packageService.add(packages,new Context(null, c.getSchema()));
		} catch (Exception e) {
			System.out.println("Error while saving Service "+packages);
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
	  public Integer update(@RequestBody Packages packages, HttpServletRequest  request) throws Exception {
		 System.out.println("offfer "+packages+" id is "+packages.getId());
		 Company c=getCompany(request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 System.out.println("CONTEXT IS"+ctx);
		  Integer id=null;
		try {
			id = packageService.update(packages,ctx);
		} catch (Exception e) {
			System.out.println("Error while updating Service "+packages);
			e.printStackTrace();
		}
		  System.out.println("in insert id "+id);
		  return id;
	  }
	 
	 @RequestMapping(value = "/list/{offset}", method = RequestMethod.GET)
	  public  List<Packages> findNext(@PathVariable("offset") Integer offset) {
		 System.out.println("findAll ");
		 List<Packages> packages=packageService.findNext(offset,null);
		  System.out.println("in insert companies "+packages);
		  return packages;
	  }
	 
	 @RequestMapping(value = "/getAll", method = RequestMethod.GET)
	  public  List<Packages> findAll(HttpServletRequest request) throws Exception {
		 System.out.println("findAll ");
		 Company c=getCompany( request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 List<Packages> packages=packageService.findAll(ctx);
		  System.out.println("in insert companies "+packages);
		  return packages;
	  }
	 
	 @RequestMapping(value = "/getoffer/{packageId}", method = RequestMethod.GET)
	  public  Packages getService(@PathVariable("packageId") Integer packageId,HttpServletRequest request) throws Exception {
		 System.out.println("findAll ");
		 Company c=getCompany( request);
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		return packageService.findById(packageId, ctx);
	  }

}
