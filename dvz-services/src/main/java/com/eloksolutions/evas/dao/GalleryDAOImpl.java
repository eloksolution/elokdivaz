package com.eloksolutions.evas.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.model.Company;
import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Gallary;
import com.eloksolutions.evas.model.Service;
@Repository("galleryDao")

public class GalleryDAOImpl implements GalleryDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Override
	public List<Gallary> findAll(Context ctx) {
		System.out.println("Context "+ctx);
		return jdbcTemplate.query(
                "SELECT ID,IMAGE_PATH,DESCRIPTION,CATEGORY,CREATE_DATE FROM "+ctx.getSchema()+".gallary",
                (rs, rowNum) -> new Gallary(rs.getInt("ID"),
                        rs.getString("IMAGE_PATH"), rs.getString("DESCRIPTION"),
                        rs.getString("CATEGORY"),rs.getInt("CREATE_DATE")
                       
                        ));
	}

	@Override
	public List<Gallary> findByColumn(String columnName, String columnValue, Context ctx) {
		return jdbcTemplate.query(
                "SELECT ID,IMAGE_PATH,DESCRIPTION,CATEGORY,CREATE_DATE FROM "+ctx.getSchema()+".gallary",
                (rs, rowNum) -> new Gallary(rs.getInt("ID"),
                        rs.getString("IMAGE_PATH"), rs.getString("DESCRIPTION"),
                        rs.getString("CATEGORY"),rs.getInt("CREATE_DATE")
                       
                        ));
	}

	@Override
	public List<Gallary> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Gallary findById(Integer id, Context ctx) {
		return (Gallary) jdbcTemplate.query(
                "SELECT ID,IMAGE_PATH,DESCRIPTION,CATEGORY,CREATE_DATE FROM "+ctx.getSchema()+".gallary"+ " WHERE id=?",
                new Object[] { id },
                (rs, rowNum) -> new Gallary(rs.getInt("ID"),
                        rs.getString("IMAGE_PATH"), rs.getString("DESCRIPTION"),
                        rs.getString("CATEGORY"),rs.getInt("CREATE_DATE")
                       
                        ));
	}

	@Override
	public Integer add(Gallary model, Context ctx) {
	
		return jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".gallary (IMAGE_PATH, DESCRIPTION, CATEGORY, CREATE_DATE)"
                		+ " VALUES (?, ?, ?, ?)",
                new Object[] { model.getImagePath(),model.getDescription(),model.getCategory(),
                		new Date(new java.util.Date().getTime())
                		
                }
        );
	}

	@Override
	public Integer update(Gallary model, Context ctx) {
		// TODO Auto-generated method stub
		return jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".gallary (IMAGE_PATH, DESCRIPTION, CATEGORY, CREATE_DATE)"
                		+ " VALUES (?, ?, ?, ?)",
                new Object[] { model.getImagePath(),model.getDescription(),model.getCategory(),
                		new Date(new java.util.Date().getTime())
                		
                }
        );
	}

	@Override
	public Integer delete(Gallary model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
