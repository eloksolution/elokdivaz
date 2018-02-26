package com.eloksolutions.evas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Service;

@Repository("servicesDAO")
public class SevicesDAOImpl implements ServicesDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Service> findAll(Context ctx) {
		System.out.println("Context "+ctx);
		return jdbcTemplate.query(
                "SELECT ID,NAME,DESCRIPTION,IMG_PATH,PRICE,DISCOUNT,IMG_ICON,CREATE_DATE,UPDATE_DATE FROM "+ctx.getSchema()+".ServiceS",
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
                "SELECT ID,NAME,DESCRIPTION,IMG_PATH,PRICE,DISCOUNT,IMG_ICON,CREATE_DATE,UPDATE_DATE FROM "+ctx.getSchema()+".SERVICES"
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
                "SELECT ID,NAME,DESCRIPTION,IMG_PATH,PRICE,DISCOUNT,IMG_ICON,CREATE_DATE,UPDATE_DATE FROM "+ctx.getSchema()+".SERVICES"
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
		return jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".SERVICES ( NAME,DESCRIPTION,IMG_PATH,PRICE,DISCOUNT,IMG_ICON,CREATE_DATE,UPDATE_DATE)"
                		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[] { model.getName(),model.getDescription(),model.getImagePath(),
                		model.getPrice(),model.getDiscount(),
                		model.getImageIcon(),model.getCreateDate(),model.getUpdateDate()
                }
        );
	}

	@Override
	public Integer update(Service model, Context ctx) {
		return jdbcTemplate.update(
                "UPDATE "+ctx.getSchema()+".SERVICES SET NAME = ?, DESCRIPTION = ?, IMG_PATH = ?, PRICE = ?, DISCOUNT = ?,IMG_ICON=?,"
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
