package in.eloksolutions.evas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Feedback;

@Repository("feedbackDAO")
public class FeedbackDAOImpl implements FeedbackDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer add(Feedback model, Context ctx) {
		Integer i= jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".feedback (SERVICE_ID, TYPE, DESCRIPTION,USER_ID, CREATE_DATE,RATING"
                		+ " ) VALUES ( ?,?,?,?,NOW(),?); ",
                new Object[] { model.getServiceId(),model.getType(),model.getDescription(),model.getUserId(),model.getRating()}
        );
		
		return i;
	}
	@Override
	public List<Feedback> findAll(Context ctx) {
		System.out.println("Context "+ctx);
		return jdbcTemplate.query(
                "SELECT ID,SERVICE_ID,TYPE,DESCRIPTION,CREATE_DATE,USER_ID FROM  "+ctx.getSchema()+".feedback",
                (rs, rowNum) -> new Feedback(rs.getInt("ID"),
                        rs.getInt("SERVICE_ID"), 
                        rs.getString("DESCRIPTION"),rs.getString("TYPE"), rs.getInt("USER_ID"),rs.getTimestamp("CREATE_DATE")
                        )
        );
	}

	@Override
	public List<Feedback> findByColumn(String columnName, String columnValue, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Feedback> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Feedback findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Feedback model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(Feedback model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}
}

	


