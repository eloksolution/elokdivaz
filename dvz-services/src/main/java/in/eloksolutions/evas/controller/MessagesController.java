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
import in.eloksolutions.evas.model.Message;
import in.eloksolutions.evas.model.Offer;
import in.eloksolutions.evas.services.MessagesServices;

@RestController

@RequestMapping("/messages")

public class MessagesController {
@Autowired
private MessagesServices messagesServices;
 @RequestMapping(value = "/add", method = RequestMethod.POST)
 public Integer add(@RequestBody Message message, HttpServletRequest request) throws Exception{
	 System.out.println("messageDao  Values"+message);
	 
	 Company c=getCompany(request);
	  Integer id;
	try {	
		id = messagesServices.add(message,new Context(null, c.getSchema(),c.getId(),c.getName()));
	} catch (Exception e) {
		System.out.println("Error while saving Service "+message);
		e.printStackTrace();
		throw new Exception("Error while saving Message "+e.getMessage());
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
 public  List<Message> findAll(HttpServletRequest request) throws Exception {
	 System.out.println("findAll");
	 Company c=getCompany( request);
	 System.out.println("Cache company is"+c);
	 Context ctx=new Context(null,c.getSchema());
	 List<Message> messages=messagesServices.findAll(ctx);
	  System.out.println("in insert companies "+messages);
	  return messages;
 }
 @RequestMapping(value = "/update", method = RequestMethod.POST)
 public Integer update(@RequestBody Message message, HttpServletRequest  request) throws Exception {
	 System.out.println("messagea "+message+" id is "+message.getId());
	 Company c=getCompany(request);
	 System.out.println("Cache company is"+c);
	 Context ctx=new Context(null,c.getSchema());
	 System.out.println("CONTEXT IS"+ctx);
	  Integer id=null;
	try {
		id = messagesServices.update(message,ctx);
	} catch (Exception e) {
		System.out.println("Error while updating Service "+message);
		e.printStackTrace();
	}
	  System.out.println("in insert id "+id);
	  return id;
 }

@RequestMapping(value = "/list/{offset}", method = RequestMethod.GET)
 public  List<Message> findNext(@PathVariable("offset") Integer offset) {
	 System.out.println("findAll ");
	 List<Message> companies=messagesServices.findNext(offset,null);
	  System.out.println("in insert companies "+companies);
	  return companies;
 }


@RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
 public  Message getService(@PathVariable("messageId") Integer messageId,HttpServletRequest request) throws Exception {
	 System.out.println("findAll ");
	 Company c=getCompany( request);
	 System.out.println("Cache company is"+c);
	 Context ctx=new Context(null,c.getSchema());
	return messagesServices.findById(messageId, ctx);
 } 

}
