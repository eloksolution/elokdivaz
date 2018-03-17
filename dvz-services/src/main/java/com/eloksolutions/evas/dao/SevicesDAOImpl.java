package com.eloksolutions.evas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Service;
import com.mysql.jdbc.Statement;

@Repository("servicesDAO")
public class SevicesDAOImpl implements ServicesDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Service> findAll(Context ctx) {
		System.out.println("Context "+ctx);
		return jdbcTemplate.query(
                "SELECT ID,NAME,DESCRIPTION,IMG_PATH,PRICE,DISCOUNT,IMG_ICON,CREATE_DATE,UPDATE_DATE FROM "+ctx.getSchema()+".services",
                (rs, rowNum) -> new Service(rs.getInt("ID"),
                        rs.getString("NAME"), rs.getString("DESCRIPTION"),
                        rs.getString("IMG_PATH"),rs.getInt("PRICE"),
                        rs.getInt("DISCOUNT"),rs.getString("IMG_ICON"),
                        rs.getDate("CREATE_DATE"),rs.getDate("UPDATE_DATE")
                        )
        );
	}

	@Override
	public List<Service> findByColumn(String columnName, String columnValue, Context ctx) {
		return jdbcTemplate.query(
                "SELECT ID,NAME,DESCRIPTION,IMG_PATH,PRICE,DISCOUNT,IMG_ICON,CREATE_DATE,UPDATE_DATE FROM "+ctx.getSchema()+".services"
                		+ " WHERE "+columnName+" = '"+columnValue+"'",
                        (rs, rowNum) -> new Service(rs.getInt("ID"),
                                rs.getString("NAME"), rs.getString("DESCRIPTION"),
                                rs.getString("IMG_PATH"),rs.getInt("PRICE"),
                                rs.getInt("DISCOUNT"),rs.getString("IMG_ICON"),
                                rs.getDate("CREATE_DATE"),rs.getDate("UPDATE_DATE")
                                )
        );
	}

	@Override
	public List<Service> findNext(Integer noOfRecords, Context ctx) {
		return null;
	}

	@Override
	public Service findById(Integer id, Context ctx) {
		return jdbcTemplate.queryForObject(
                "SELECT ID,NAME,DESCRIPTION,IMG_PATH,PRICE,DISCOUNT,IMG_ICON,CREATE_DATE,UPDATE_DATE FROM "+ctx.getSchema()+".services"
                		+ " WHERE id=?",
                		new Object[] { id },
                		(rs, rowNum) -> new Service(rs.getInt("ID"),
                                rs.getString("NAME"), rs.getString("DESCRIPTION"),
                                rs.getString("IMG_PATH"),rs.getInt("PRICE"),
                                rs.getInt("DISCOUNT"),rs.getString("IMG_ICON"),
                                rs.getDate("CREATE_DATE"),rs.getDate("UPDATE_DATE")
                                )
        );
	}

	@Override
	public Integer add(Service model, Context ctx) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement statement = con.prepareStatement("INSERT INTO " + ctx.getSchema() + ".services ( "
						+ "NAME,DESCRIPTION,IMG_PATH,PRICE,DISCOUNT,IMG_ICON,CREATE_DATE,UPDATE_DATE)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, model.getName());
				statement.setString(2, model.getDescription());
				statement.setString(3, model.getImagePath());
				statement.setInt(4, model.getPrice());
				statement.setInt(5, model.getDiscount());
				statement.setString(6, model.getImageIcon());
				statement.setTimestamp(7, new java.sql.Timestamp((new Date().getTime())));
				statement.setTimestamp(8,new java.sql.Timestamp((new Date().getTime())));

				return statement;
			}
		}, holder);
		return holder.getKey().intValue();
	}

	@Override
	public Integer update(Service model, Context ctx) {
		return jdbcTemplate.update(
                "UPDATE "+ctx.getSchema()+".services SET NAME = ?, DESCRIPTION = ?, IMG_PATH = ?, PRICE = ?, DISCOUNT = ?,IMG_ICON=?,"
                		+ " UPDATE_DATE=? WHERE ID = ?",
                new Object[] { model.getName(),model.getDescription(),model.getImagePath(),
                		model.getPrice(),model.getDiscount(), model.getImageIcon(),
                		model.getUpdateDate(),model.getId()
                }
        );
	}

	@Override
	public Integer delete(Service model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
