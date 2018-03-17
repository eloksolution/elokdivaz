package com.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.dao.MessagesDAO;
import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Message;
@Repository("messageService")
public class MessageServiceImpl implements MessagesServices{
	@Autowired
    private MessagesDAO messagesDao;
	@Override
	public Integer add(Message message, Context ctx) throws Exception {
		
		return messagesDao.add(message, ctx);
	}

	@Override
	public List<Message> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findAll(Context ctx) {
		
		return messagesDao.findAll(ctx);
	}

	@Override
	public Message findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Message model, Context ctx) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}
