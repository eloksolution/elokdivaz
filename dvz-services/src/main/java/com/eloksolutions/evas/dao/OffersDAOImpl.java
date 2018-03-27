package com.eloksolutions.evas.dao;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Offer;
import com.eloksolutions.evas.model.Service;

@Repository("offersDAO")
public class OffersDAOImpl implements OffersDAO{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Offer> findAll(Context ctx) {
		return jdbcTemplate.query(
                "SELECT ID,NAME,DESCRIPTION,OFFER_PRICE,BEFORE_OFFER_PRICE,IMG_PATH,START_DATE,END_DATE,CREATED_DATE FROM "+ctx.getSchema()+".offers",
                (rs, rowNum) -> new Offer(rs.getInt("ID"),
                        rs.getString("NAME"), rs.getString("DESCRIPTION"),
                        rs.getInt("OFFER_PRICE"),rs.getInt("BEFORE_OFFER_PRICE"),rs.getString("IMG_PATH")
                       ,rs.getString("START_DATE"),rs.getString("END_DATE"),rs.getString("CREATED_DATE")
                        ));
		
	}

	@Override
	public List<Offer> findByColumn(String columnName, String columnValue, Context ctx) {
		return jdbcTemplate.query(
                "SELECT ID,NAME,DESCRIPTION,OFFER_PRICE,BEFORE_OFFER_PRICE,IMG_PATH,START_DATE,END_DATE,CREATED_DATE FROM "+ctx.getSchema()+".offers"+ " WHERE "+columnName+" = '"+columnValue+"'",
                (rs, rowNum) -> new Offer(rs.getInt("ID"),
                        rs.getString("NAME"), rs.getString("DESCRIPTION"),
                        rs.getInt("OFFER_PRICE"),rs.getInt("BEFORE_OFFER_PRICE"),rs.getString("IMG_PATH")
                       ,rs.getString("START_DATE"),rs.getString("END_DATE"),rs.getString("CREATED_DATE")
                        ));
	}

	@Override
	public List<Offer> findNext(Integer noOfRecords, Context ctx) {
		return null;
	}

	@Override
	public Offer findById(Integer id, Context ctx) {
		return jdbcTemplate.queryForObject(
                "SELECT ID,NAME,DESCRIPTION,OFFER_PRICE,BEFORE_OFFER_PRICE,IMG_PATH,START_DATE,END_DATE,CREATED_DATE FROM "+ctx.getSchema()+".offers"
                		+ " WHERE id=?",
                		new Object[] { id },
                		(rs, rowNum) -> new Offer(rs.getInt("ID"),
                				 rs.getString("NAME"), rs.getString("DESCRIPTION"),
                                 rs.getInt("OFFER_PRICE"),rs.getInt("BEFORE_OFFER_PRICE"),rs.getString("IMG_PATH")
                                ,rs.getString("START_DATE"),rs.getString("END_DATE"),rs.getString("CREATED_DATE")
                                )
        );
		
	}

	@Override
	public Integer add(Offer model, Context ctx) {
		return jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".offers (NAME,  DESCRIPTION, OFFER_PRICE,  BEFORE_OFFER_PRICE, IMG_PATH, START_DATE, END_DATE, CREATED_DATE)"
                		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                new Object[] { model.getName(),model.getDescription(),model.getOfferPrice(),
                		model.getBeforeOfferPrice(),model.getImgePath(), model.getStartDate(),
                		model.getEndDate(),new Date(new java.util.Date().getTime())
                		
                }
        );
	}

	@Override
	public Integer update(Offer model, Context ctx) {
		return jdbcTemplate.update(
                "UPDATE "+ctx.getSchema()+".offers SET name = ?, description = ?, offer_price = ?, before_offer_price = ?, img_path = ?,start_date=?,"
                		+ " end_date=? WHERE ID = ?",
                new Object[] { model.getName(),model.getDescription(),model.getOfferPrice(),
                		model.getBeforeOfferPrice(),model.getImgePath(), model.getStartDate(),
                		model.getEndDate(),model.getId()
                }
        );
	}

	@Override
	public Integer delete(Offer model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}
	

	

	

}
