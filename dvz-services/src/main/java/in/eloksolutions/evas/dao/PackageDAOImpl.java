package in.eloksolutions.evas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Offer;
import in.eloksolutions.evas.model.Packages;
@Repository("packageDAO")
public class PackageDAOImpl implements PackageDAO{
@Autowired
private JdbcTemplate jdbcTemplate;
	@Override
	public List<Packages> findAll(Context ctx) {
		
		return jdbcTemplate.query( "SELECT ID,NAME, DESCRIPTION, PRICE, CATEGORY, IMAGE_PATH FROM "+ctx.getSchema()+".package",
                (rs, rowNum) -> new Packages(rs.getInt("ID"),
                        rs.getString("NAME"), rs.getString("DESCRIPTION"),
                        rs.getInt("PRICE"),rs.getString("CATEGORY"),rs.getString("IMAGE_PATH")
                        ));
	}

	@Override
	public List<Packages> findByColumn(String columnName, String columnValue, Context ctx) {
		return jdbcTemplate.query(
                "SELECT ID,NAME, DESCRIPTION, PRICE, CATEGORY, IMAGE_PATH FROM "+ctx.getSchema()+".package"+ " WHERE "+columnName+" = '"+columnValue+"'",
                (rs, rowNum) -> new Packages(rs.getInt("ID"),
                        rs.getString("NAME"), rs.getString("DESCRIPTION"),
                        rs.getInt("PRICE"),rs.getString("CATEGORY"),rs.getString("IMAGE_PATH")
                        ));
	}

	@Override
	public List<Packages> findNext(Integer noOfRecords, Context ctx) {
		return null;
	}

	@Override
	public Packages findById(Integer id, Context ctx) {
		return jdbcTemplate.queryForObject(
                "SELECT ID,NAME, DESCRIPTION, PRICE, CATEGORY, IMAGE_PATH FROM "+ctx.getSchema()+".package"
                		+ " WHERE id=?",
                		new Object[] { id },
                		 (rs, rowNum) -> new Packages(rs.getInt("ID"),
                                 rs.getString("NAME"), rs.getString("DESCRIPTION"),
                                 rs.getInt("PRICE"),rs.getString("CATEGORY"),rs.getString("IMAGE_PATH")
                                 ));
	}

	@Override
	public Integer add(Packages model, Context ctx) {
		return jdbcTemplate.update(
                "INSERT INTO "+ctx.getSchema()+".package (NAME, DESCRIPTION, PRICE, CATEGORY, IMAGE_PATH)"
                		+ " VALUES (?, ?, ?, ?, ?)",
                new Object[] { model.getName(),model.getDescription(),model.getPrice(),
                		model.getCategory(),model.getImagePath()
                		
                		
                }
        );
	}

	@Override
	public Integer update(Packages model, Context ctx) {
		return jdbcTemplate.update(
                "UPDATE "+ctx.getSchema()+".package SET name = ?, description = ?, price = ?, category = ?, image_path = ?  WHERE ID = ?",
                		
                new Object[] { model.getName(),model.getDescription(),model.getPrice(),
                		model.getCategory(),model.getImagePath(), model.getId()
                }
        );
	}

	@Override
	public Integer delete(Packages model, Context ctx) {
		return null;
	}

}
