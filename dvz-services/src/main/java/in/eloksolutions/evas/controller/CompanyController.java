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
import in.eloksolutions.evas.services.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
    private CompanyService companyService;
	
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	  public Integer add(@RequestBody Company company) {
		 System.out.println("Company "+company);
		  Integer id;
		try {
			id = companyService.add(company,null);
		} catch (Exception e) {
			System.out.println("Error while saving Company "+company);
			e.printStackTrace();
			return 0;
		}
		  System.out.println("BEW in insert id "+id);
		  return id;
	  }
	 
	 @RequestMapping(value = "/update", method = RequestMethod.POST)
	  public Integer update(@RequestBody Company company) {
		 System.out.println("Company "+company+" id is "+company.getId());
		 Company c=Application.getCompanyId(company.getId());
		 System.out.println("Cache company is"+c);
		 Context ctx=new Context(null,c.getSchema());
		 System.out.println("CONTEXT IS"+ctx);
		  Integer id=null;
		try {
			id = companyService.update(company,ctx);
		} catch (Exception e) {
			System.out.println("Error while updating company "+company);
			e.printStackTrace();
		}
		  System.out.println("in insert id "+id);
		  return id;
	  }
	 
	 @RequestMapping(value = "/list/{offset}", method = RequestMethod.GET)
	  public  List<Company> findNext(@PathVariable("offset") Integer offset) {
		 System.out.println("findAll ");
		 List<Company> companies=companyService.findNext(offset,null);
		  System.out.println("in insert companies "+companies);
		  return companies;
	  }
	 
	 @RequestMapping(value = "/getAll", method = RequestMethod.GET)
	  public  List<Company> findAll() {
		 System.out.println("findAll ");
		 List<Company> companies=companyService.findAll(null);
		  System.out.println("in insert companies "+companies);
		  return companies;
	  }
	 
	 @RequestMapping(value = "/{companyId}", method = RequestMethod.GET)
	  public  Company getCompany(@PathVariable("companyId") Integer companyId,HttpServletRequest request) throws Exception {
		 System.out.println("getCompany ");
		return companyService.findById(companyId, null);
	  }
}