package in.eloksolutions.evas.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import in.eloksolutions.evas.Application;
import in.eloksolutions.evas.model.Company;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Feedback;
import in.eloksolutions.evas.services.FeedbackServices;

@RestController

@RequestMapping("/feedback")
public class FeedbackController {
@Autowired

private FeedbackServices feedbackService;

@RequestMapping(value = "/add", method = RequestMethod.POST)
 public Integer add(@RequestBody Feedback feedback, HttpServletRequest request) throws Exception{
	 System.out.println("feedback  Values"+feedback);
	 
	 Company c=getCompany(request);
	  Integer id;
	try {	
		id = feedbackService.add(feedback,new Context(null, c.getSchema(),c.getId(),c.getName()));
	} catch (Exception e) {
		System.out.println("Error while saving Service "+feedback);
		e.printStackTrace();
		throw new Exception("Error while saving Message "+e.getMessage());
	}
	  System.out.println("in insert id "+id);
	  return id;	 
	 
 }
 
 @RequestMapping(value = "/getAll", method = RequestMethod.GET)
 public  List<Feedback> findAll(HttpServletRequest request) throws Exception {
	 System.out.println("findAll");
	 Company c=getCompany( request);
	 System.out.println("Cache company is"+c);
	 Context ctx=new Context(null,c.getSchema());
	 List<Feedback> feedbacks=feedbackService.findAll(ctx);
	  System.out.println("in insert companies "+feedbacks);
	  return feedbacks;
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
}
