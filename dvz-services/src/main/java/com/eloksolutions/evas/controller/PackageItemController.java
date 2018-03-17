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
import com.eloksolutions.evas.model.PackageItem;
import com.eloksolutions.evas.services.PackageItemServices;

@RestController
@RequestMapping("packagesitems")
public class PackageItemController {
@Autowired
PackageItemServices PackageItemServices;
@RequestMapping(value = "/add", method = RequestMethod.POST)
public Integer add(@RequestBody PackageItem packageItem, HttpServletRequest request) throws Exception{
	 System.out.println("messageDao  Values"+packageItem);
	 
	 Company c=getCompany(request);
	  Integer id;
	try {	
		id = PackageItemServices.add(packageItem,new Context(null, c.getSchema()));
	} catch (Exception e) {
		System.out.println("Error while saving Service "+packageItem);
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
public Integer update(@RequestBody PackageItem packageItem, HttpServletRequest  request) throws Exception {
	 System.out.println("PackageItem "+packageItem+" id is "+packageItem.getId());
	 Company c=getCompany(request);
	 System.out.println("Cache company is"+c);
	 Context ctx=new Context(null,c.getSchema());
	 System.out.println("CONTEXT IS"+ctx);
	  Integer id=null;
	try {
		id = PackageItemServices.update(packageItem,ctx);
	} catch (Exception e) {
		System.out.println("Error while updating Service "+packageItem);
		e.printStackTrace();
	}
	  System.out.println("in insert id "+id);
	  return id;
}

@RequestMapping(value = "/list/{offset}", method = RequestMethod.GET)
public  List<PackageItem> findNext(@PathVariable("offset") Integer offset) {
	 System.out.println("findAll ");
	 List<PackageItem> packageItems=PackageItemServices.findNext(offset,null);
	  System.out.println("in insert companies "+packageItems);
	  return packageItems;
}

@RequestMapping(value = "/getAll", method = RequestMethod.GET)
public  List<PackageItem> findAll(HttpServletRequest request) throws Exception {
	 System.out.println("findAll ");
	 Company c=getCompany( request);
	 System.out.println("Cache company is"+c);
	 Context ctx=new Context(null,c.getSchema());
	 List<PackageItem> packageItems=PackageItemServices.findAll(ctx);
	  System.out.println("in insert companies "+packageItems);
	  return packageItems;
}

@RequestMapping(value = "/getoffer/{packageItemId}", method = RequestMethod.GET)
public  PackageItem getService(@PathVariable("offerId") Integer packageItemId,HttpServletRequest request) throws Exception {
	 System.out.println("findAll ");
	 Company c=getCompany( request);
	 System.out.println("Cache company is"+c);
	 Context ctx=new Context(null,c.getSchema());
	return PackageItemServices.findById(packageItemId, ctx);
}

}
