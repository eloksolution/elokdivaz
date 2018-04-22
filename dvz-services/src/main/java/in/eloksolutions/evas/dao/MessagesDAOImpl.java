package in.eloksolutions.evas.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Message;

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
		
		return jdbcTemplate.query(
                "SELECT ID,SUBJECT,DESCRIPTION,IMG_PATH,CREATE_DATE,TYPE FROM "+ctx.getSchema()+".messages"+ " WHERE "+columnName+" = '"+columnValue+"'",
                (rs, rowNum) -> new Message(rs.getInt("ID"),
                        rs.getString("SUBJECT"), rs.getString("DESCRIPTION"),
                        rs.getString("IMG_PATH"),rs.getInt("CREATE_DATE"),rs.getString("TYPE")
                       
                        ));
	}

	@Override
	public List<Message> findNext(Integer noOfRecords, Context ctx) {
		return null;
	}

	@Override
	public Message findById(Integer id, Context ctx) {
		return jdbcTemplate.queryForObject(
                "SELECT ID,SUBJECT,DESCRIPTION,IMG_PATH,CREATE_DATE,TYPE FROM "+ctx.getSchema()+".messages"
                		+ " WHERE id=?",
                		new Object[] { id },
                		 (rs, rowNum) -> new Message(rs.getInt("ID"),
                                 rs.getString("SUBJECT"), rs.getString("DESCRIPTION"),
                                 rs.getString("IMG_PATH"),rs.getInt("CREATE_DATE"),rs.getString("TYPE")
                                
                                 ));
	}

	@Override
	public Integer add(Message model, Context ctx) {
	
		Integer i= jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".messages (SUBJECT, DESCRIPTION, IMG_PATH, CREATE_DATE, TYPE)"
                		+ " VALUES (?, ?, ?, ?, ?)",
                new Object[] { model.getSubject(),model.getDescription(),model.getImg_path(),
                		new Date(new java.util.Date().getTime()),model.getType()
                		
                }
        );
		if(i>0){
			
		}
		return i;
	}

	@Override
	public Integer update(Message model, Context ctx) {
		return jdbcTemplate.update(
                "UPDATE "+ctx.getSchema()+".messages SET subject = ?, description = ?, img_path = ? , type = ? WHERE ID = ?",
                new Object[] { model.getSubject(),model.getDescription(),model.getImg_path(),
                		model.getType(),model.getId()
                }
        );
	}

	@Override
	public Integer delete(Message model, Context ctx) {
		return null;
	}

	
	
	}

	


