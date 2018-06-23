package in.eloksolutions.evas.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;

import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Customer;
import in.eloksolutions.evas.util.ParlourFormattor;
@Repository("customerDAO")
public class CustomerDAOImpl implements CustomerDAO{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public Integer add(Customer customer,Context  ctx) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		try{
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				 PreparedStatement statement = con.prepareStatement("INSERT INTO elokevasdb.customer ("
				 		+ "FIRST_NAME, LAST_NAME, EMAIL, ADDRESS_1, ADDRESS_2, CITY, STATE, "
				 		+ "CREATE_DATE, UPDATE_DATE, LATITUDE, LONGITUDE, PHONE, IMAGE_PATH) VALUES"
				 		+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);  ", Statement.RETURN_GENERATED_KEYS);
			        statement.setString(1, customer.getFirstName());
			        statement.setString(2, customer.getLastName());
			        statement.setString(3, customer.getEmail());
			        statement.setString(4,  customer.getAddress1());
			        statement.setString(5, customer.getAddress2());
			        statement.setString(6, customer.getCity());
			        statement.setString(7,  customer.getState());
			        statement.setDate(8, new Date(new java.util.Date().getTime()));
			        statement.setDate(9, new Date(new java.util.Date().getTime()));
			        statement.setString(10, customer.getLatitude());
			        statement.setString(11, customer.getLongitude());
			        statement.setString(12, customer.getPhone());
			        statement.setString(13, customer.getImagePath());
			        return statement;
			}
		},holder);
		}catch(Exception ex){
			ex.printStackTrace();
			if(ex.getMessage().contains("Duplicate")){
				System.out.println("Customer already exist ");
				Customer c=findByEmailOrMobile(customer.getEmail(), customer.getPhone());
				return c.getId();
			}
			throw ex;
		}
		return holder.getKey().intValue();
	}
	
	@Override
	public List<Customer> findAll(Context ctx) {
		  List<Customer> result = jdbcTemplate.query(
        "SELECT * FROM elokevasdb.customer " ,
        (rs, rowNum) -> new Customer(rs.getInt("id"),
                rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"),
                rs.getString("ADDRESS_1"), rs.getString("ADDRESS_2"),
                rs.getString("CITY"), rs.getString("STATE"),ParlourFormattor.parse(rs.getString("MY_PARALORS")),
                rs.getDate("CREATE_DATE"),rs.getDate("UPDATE_DATE"),rs.getString("LATITUDE"), rs.getString("LONGITUDE"),
                rs.getString("PHONE"), rs.getString("IMAGE_PATH"), rs.getString("DEVICE_TOKEN")
                ));
		  System.out.println("in find all result "+result);
		return result;
	}
	@Override
	public List<Customer> findByColumn(String columnName, String columnValue, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Customer> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Customer findById(Integer id, Context ctx) {
		return  jdbcTemplate.queryForObject(
		        "SELECT * FROM elokevasdb.customer WHERE ID=?" ,
		        (rs, rowNum) -> new Customer(rs.getInt("id"),
		                rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"),
		                rs.getString("ADDRESS_1"), rs.getString("ADDRESS_2"),
		                rs.getString("CITY"), rs.getString("STATE"),ParlourFormattor.parse(rs.getString("MY_PARALORS")),
		                rs.getDate("CREATE_DATE"),rs.getDate("UPDATE_DATE"),rs.getString("LATITUDE"), rs.getString("LONGITUDE"),
		                rs.getString("PHONE"), rs.getString("IMAGE_PATH"), rs.getString("DEVICE_TOKEN")
		                ),new Object[] { id});
	}
	
	@Override
	public Customer findByEmailOrMobile(String  email,String mobile) {
		return  jdbcTemplate.queryForObject(
		        "SELECT * FROM elokevasdb.customer WHERE EMAIL=? OR PHONE=? LIMIT 1" ,
		        (rs, rowNum) -> new Customer(rs.getInt("id"),
		                rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"),
		                rs.getString("ADDRESS_1"), rs.getString("ADDRESS_2"),
		                rs.getString("CITY"), rs.getString("STATE"),ParlourFormattor.parse(rs.getString("MY_PARALORS")),
		                rs.getDate("CREATE_DATE"),rs.getDate("UPDATE_DATE"),rs.getString("LATITUDE"), rs.getString("LONGITUDE"),
		                rs.getString("PHONE"), rs.getString("IMAGE_PATH"), rs.getString("DEVICE_TOKEN")
		                ),new Object[] { email,mobile});
	}
	@Override
	public Integer update(Customer model, Context ctx) {
		 return jdbcTemplate
				.update("UPDATE elokevasdb.customer "
						+ "SET FIRST_NAME = ? "
						+ "LAST_NAME=? "
						+ "IMAGE_PATH=? "
						+ "EMAIL=? "
						+ "WHERE ID = ?",
						new Object[] {model.getFirstName(),model.getLastName(),model.getImagePath(),model.getEmail(),model.getId()});
	}
	@Override
	public Integer delete(Customer model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateDeviceToken(String  userid, String token) {
		return jdbcTemplate
				.update("UPDATE elokevasdb.customer SET device_token = ? WHERE ID = ?",
						new Object[] { token,userid});
	}

	@Override
	public Integer updateMyRecentParlours(Integer userid,String myRecentParloursJSON) {
		 return jdbcTemplate
					.update("UPDATE elokevasdb.customer "
							+ "SET MY_PARALORS = ? "
							+ " WHERE ID = ?",
							new Object[] {myRecentParloursJSON,userid});
	}
}
