package in.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.dao.CompanyCustomerDAO;
import in.eloksolutions.evas.dao.MessagesDAO;
import in.eloksolutions.evas.messaging.SendNotification;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Customer;
import in.eloksolutions.evas.model.Message;
@Repository("messageService")
public class MessageServiceImpl implements MessagesServices{
	@Autowired
    private MessagesDAO messagesDao;
	
	@Autowired
    private CompanyCustomerDAO companyCustomerDAO;
	
	@Override
	public Integer add(Message message, Context ctx) throws Exception {
		Integer id= messagesDao.add(message, ctx);
		if(id>0){
			try {
				List<Customer> customers=companyCustomerDAO.getCustomers(ctx);
				SendNotification.sendOfferMessageToUsers(customers, message.getDescription(), message.getSubject());
			} catch (Exception e) {
				System.out.println("Error while sending message "+e.getMessage());
				e.printStackTrace();
			}
		}
		return id;
	}

	@Override
	public List<Message> findNext(Integer noOfRecords, Context ctx) {
		return messagesDao.findNext(noOfRecords,ctx);
	}

	@Override
	public List<Message> findAll(Context ctx) {
		
		return messagesDao.findAll(ctx);
	}

	@Override
	public Message findById(Integer id, Context ctx) {
		return messagesDao.findById(id, ctx);
	}

	@Override
	public Integer update(Message model, Context ctx) throws Exception {
		return messagesDao.update(model, ctx);
	}
	

}
