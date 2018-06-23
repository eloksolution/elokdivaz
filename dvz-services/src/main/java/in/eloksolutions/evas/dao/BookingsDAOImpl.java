package in.eloksolutions.evas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;

import in.eloksolutions.evas.model.Booking;
import in.eloksolutions.evas.model.Context;

@Repository("bookingsDAO")
public class BookingsDAOImpl implements BookingsDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Booking> findAll(Context ctx) {
		System.out.println("Context "+ctx);
		return jdbcTemplate.query(
                "SELECT ID,CUSTOMER_NAME,CUSTOMER_ID,ORDER_DATE,ORDER_ITEMS,TOTAL_PRICE,EMAIL,PHONE FROM "+ctx.getSchema()+".bookings",
                (rs, rowNum) -> new Booking(rs.getInt("ID"),
                        rs.getString("CUSTOMER_NAME"), rs.getInt("CUSTOMER_ID"),
                        rs.getString("ORDER_ITEMS"),rs.getInt("TOTAL_PRICE"),
                        rs.getTimestamp("ORDER_DATE"), rs.getString("EMAIL"), rs.getString("PHONE")
                        )
        );
	}

	@Override
	public List<Booking> findByColumn(String columnName, String columnValue, Context ctx) {
		return jdbcTemplate.query(
                "SELECT ID,CUSTOMER_NAME,CUSTOMER_ID,ORDER_DATE,ORDER_ITEMS,TOTAL_PRICE,EMAIL,PHONE  FROM "+ctx.getSchema()+".bookings"
                		+ " WHERE "+columnName+" = '"+columnValue+"'",
                (rs, rowNum) -> new Booking(rs.getInt("ID"),
                        rs.getString("CUSTOMER_NAME"), rs.getInt("CUSTOMER_ID"),
                        rs.getString("ORDER_ITEMS"),rs.getInt("TOTAL_PRICE"),
                        rs.getTimestamp("ORDER_DATE"), rs.getString("EMAIL"), rs.getString("PHONE")
                        )
        );
	}

	@Override
	public List<Booking> findNext(Integer noOfRecords, Context ctx) {
		return null;
	}

	@Override
	public Booking findById(Integer id, Context ctx) {
		return jdbcTemplate.queryForObject(
                "SELECT ID,CUSTOMER_NAME,CUSTOMER_ID,ORDER_DATE,ORDER_ITEMS,TOTAL_PRICE,EMAIL,PHONE  FROM "+ctx.getSchema()+".bookings"
                		+ " WHERE id=?",
                		new Object[] { id },
                (rs, rowNum) -> new Booking(rs.getInt("ID"),
                        rs.getString("CUSTOMER_NAME"), rs.getInt("CUSTOMER_ID"),
                        rs.getString("ORDER_ITEMS"),rs.getInt("TOTAL_PRICE"),
                        rs.getTimestamp("ORDER_DATE"), rs.getString("EMAIL"), rs.getString("PHONE")
                        )
        );
	}

	@Override
	public Integer add(Booking model, Context ctx) {
		System.out.println(" Date is "+model.getStrOrderDate());
		SimpleDateFormat sdf=new SimpleDateFormat("E, dd MMM yyyy HH:mm");
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				 PreparedStatement statement = con.prepareStatement("INSERT INTO "+ctx.getSchema()+".bookings ( CUSTOMER_NAME, CUSTOMER_ID, ORDER_DATE, ORDER_ITEMS, TOTAL_PRICE,EMAIL,PHONE)"
                		+ " VALUES (?, ?, ?, ?, ?,?,?)", Statement.RETURN_GENERATED_KEYS);
				 statement.setString(1, model.getCustomerName());
				 statement.setInt(2, model.getCustomerId());
				 Date d=null;
					try {
						d = sdf.parse(model.getStrOrderDate());
					} catch (ParseException e) {
						e.printStackTrace();
					}
				 statement.setTimestamp(3, new java.sql.Timestamp(d.getTime()));
				 statement.setString(4, model.getStrOrderItems());
				 statement.setInt(5, model.getTotalPrice());
				 statement.setString(6, model.getEmail());
				 statement.setString(7, model.getPhone());
				 return statement;
				}
			},holder);
				 
        return holder.getKey().intValue(); 
	}

	@Override
	public Integer update(Booking model, Context ctx) {
		SimpleDateFormat sdf=new SimpleDateFormat("E, dd MMM yyyy HH:mm");
		Date orderDate=null;
		try {
			orderDate=sdf.parse(model.getStrOrderDate());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jdbcTemplate.update(
                "UPDATE "+ctx.getSchema()+".bookings SET CUSTOMER_NAME = ?, STATUS=?,UPDATED_TIME=?,CAL_EVENT_ID=?,ORDER_ITEMS=?,ORDER_DATE=?, "
                		+ "TOTAL_PRICE = ? WHERE ID = ?",
                new Object[] { model.getCustomerName()
                		,model.getStatusInt(),new java.sql.Timestamp(new Date().getTime()),model.getCalEventiD(),model.getStrOrderItems()
                		,orderDate,model.getTotalPrice(),model.getId()
                }
        );
	}

	@Override
	public Integer delete(Booking model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Booking> myBookings(Integer custId, Context ctx) {
		return jdbcTemplate.query(
                "SELECT ID,CUSTOMER_NAME,CUSTOMER_ID,ORDER_DATE,ORDER_ITEMS,TOTAL_PRICE,EMAIL,PHONE FROM "+ctx.getSchema()+".bookings"
                		+ " WHERE CUSTOMER_ID=?",
                		new Object[] { custId },
                (rs, rowNum) -> new Booking(rs.getInt("ID"),
                        rs.getString("CUSTOMER_NAME"), rs.getInt("CUSTOMER_ID"),
                        rs.getString("ORDER_ITEMS"),rs.getInt("TOTAL_PRICE"),
                        rs.getTimestamp("ORDER_DATE"), rs.getString("EMAIL"), rs.getString("PHONE")
                        )
        );
	}	

}
