package com.eloksolutions.evas.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Message;

@Repository("messagesDao")
public class MessagesDAOImpl implements MessagesDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Message> findAll(Context ctx) {
		System.out.println("Context "+ctx);
		return jdbcTemplate.query(
                "SELECT ID,SUBJECT,DESCRIPTION,IMG_PATH,CREATE_DATE,TYPE FROM "+ctx.getSchema()+".messages",
                (rs, rowNum) -> new Message(rs.getInt("ID"),
                        rs.getString("SUBJECT"), rs.getString("DESCRIPTION"),
                        rs.getString("IMG_PATH"),rs.getInt("CREATE_DATE"),rs.getString("TYPE")
                       
                        ));
	}

	@Override
	public List<Message> findByColumn(String columnName, String columnValue, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Message> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer add(Message model, Context ctx) {
	
		return jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".messages (SUBJECT, DESCRIPTION, IMG_PATH, CREATE_DATE, TYPE)"
                		+ " VALUES (?, ?, ?, ?, ?)",
                new Object[] { model.getSubject(),model.getDescription(),model.getImg_path(),
                		new Date(new java.util.Date().getTime()),model.getType()
                		
                }
        );
	}

	@Override
	public Integer update(Message model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Message model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	}

	


