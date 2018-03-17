package com.eloksolutions.evas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Offer;
import com.eloksolutions.evas.model.PackageItem;
@Repository("packageItemDao")

public class PackageItemDAOImpl implements PackageItemDAO{
@Autowired
private JdbcTemplate jdbcTemplate;
	@Override
	public List<PackageItem> findAll(Context ctx) {
		
		return jdbcTemplate.query( "SELECT ID,ITEM_ID,NAME,PRICE,CATEGORY,IMG_PATH FROM "+ctx.getSchema()+".package_items",
                (rs, rowNum) -> new PackageItem(rs.getInt("ID"),rs.getInt("ITEM_ID"),
                		rs.getString("NAME"),rs.getInt("PRICE"),rs.getString("CATEGORY"),rs.getString("IMG_PATH")
                       
                        ));
	}

	@Override
	public List<PackageItem> findByColumn(String columnName, String columnValue, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PackageItem> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PackageItem findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer add(PackageItem model, Context ctx) {
		return jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".package_items (NAME,PRICE,CATEGORY,IMG_PATH)"
                		+ " VALUES (?, ?, ?, ?)",
                new Object[] { model.getName(),model.getPrice(),model.getCategory(),model.getImagePath()
                		
                }
        );
	}

	@Override
	public Integer update(PackageItem model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(PackageItem model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

}
