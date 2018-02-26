package com.eloksolutions.evas.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.model.Company;
import com.eloksolutions.evas.model.Context;

@Repository("companyDAO")
public class CompanyDAOImpl implements CompanyDAO{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public Integer add(Company company,Context  ctx) {
		Integer id = jdbcTemplate
				.update("INSERT INTO ELOKEVASDB.COMPANY (NAME, DESCRIPTION, CODE, OFFICE_PHONE, OWNER_PHONE, "
						+ "DBSCHEMA, STATUS,CREATE_DATE, UPDATED_DATE, UPDATED_BY, ADDRESS, ADDRESS_1, ADDRESS_2,"
						+ "CITY, STATE, LATITUDE,LONGITUDE, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, LINKEDIN,"
						+ " WHATSAPP, FACEBOOK, PARA1, PARA_2,PARA_3, IMG_PATH1, IMG_PATH2, IMG_PATH3) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
						+ "?, ?, ?, ?, ?, ?, ?, ?)",
						new Object[] { company.getName(),
								company.getDescriptioin(), company.getCode(),
								company.getOfficePhone(),
								company.getOwnerPhone(), company.getSchema(),
								company.getStatus(), company.getCreateDate(),company.getUpdatedDate(),
								company.getUpdatedBy(),company.getAddress(),company.getAddress_1(),
								company.getAddress_2(),company.getCity(),company.getState(),company.getLatitude(),
								company.getLongitude(),company.getEmail(),company.getFirstName(),company.getLastName(),
								company.getPassword(),company.getLinkedin(),company.getWhatsapp(),company.getFacebook(),
								company.getPara1(),company.getPara2(),company.getPara3(),company.getImgPath1(),
								company.getImgPath2(),company.getImgPath3()});
		return id;
	}
	
	@Override
	public List<Company> findNext(Integer noOfRecords,Context  ctx) {
		return jdbcTemplate
				.query("SELECT ID,NAME,CODE,DBSCHEMA FROM ELOKEVASDB.COMPANY WHERE STATUS='Y' LIMIT 10 OFFSET ?",
						new Object[] { noOfRecords },
						(rs, rowNum) -> new Company(rs.getInt("ID"), rs.getString("NAME")
								,rs.getString("CODE"),rs.getString("DBSCHEMA")));
	}
	
	@Override
	public Integer update(Company company,Context  ctx) {
		return jdbcTemplate
				.update("UPDATE ELOKEVASDB.COMPANY SET NAME = ?, DESCRIPTION = ?, CODE = ?, OFFICE_PHONE = ?,"
						+ " OWNER_PHONE = ?, DBSCHEMA = ?, STATUS = ?, UPDATED_DATE = ?,"
						+ " UPDATED_BY = ?, ADDRESS = ?, ADDRESS_1 = ?, ADDRESS_2 = ?, CITY = ?, STATE = ?, "
						+ "LATITUDE = ?, LONGITUDE = ?, EMAIL = ?, FIRST_NAME = ?, LAST_NAME = ?, PASSWORD = ?, "
						+ "LINKEDIN = ?, WHATSAPP = ?, FACEBOOK = ?, PARA1 = ?, PARA_2 = ?, PARA_3 = ?, "
						+ "IMG_PATH1 = ?, IMG_PATH2 = ?, IMG_PATH3 = ? WHERE ID = ?",
						new Object[] { company.getName(),
								company.getDescriptioin(), company.getCode(),
								company.getOfficePhone(),
								company.getOwnerPhone(), company.getSchema(),
								company.getStatus(),company.getUpdatedDate(),
								company.getUpdatedBy(),company.getAddress(),company.getAddress_1(),
								company.getAddress_2(),company.getCity(),company.getState(),company.getLatitude(),
								company.getLongitude(),company.getEmail(),company.getFirstName(),company.getLastName(),
								company.getPassword(),company.getLinkedin(),company.getWhatsapp(),company.getFacebook(),
								company.getPara1(),company.getPara2(),company.getPara3(),company.getImgPath1(),
								company.getImgPath2(),company.getImgPath3(),company.getId()});
				
	}
	@Override
	public Integer delete(Company model,Context  ctx) {
		return null;
	}

	@Override
	public List<Company> findAll(Context  ctx) {
		return jdbcTemplate
				.query("SELECT ID,NAME,CODE,DBSCHEMA FROM ELOKEVASDB.COMPANY WHERE STATUS='Y' ",
						(rs, rowNum) -> new Company(rs.getInt("ID"), rs.getString("NAME")
								, rs.getString("CODE"),rs.getString("DBSCHEMA")));
	}

	@Override
	public List<Company> findByColumn(String columnName, String columnValue,Context ctx) {
		return jdbcTemplate
				.query("SELECT ID,NAME,CODE,DBSCHEMA FROM ELOKEVASDB.COMPANY WHERE STATUS='Y' AND "
						+ columnName + "='" + columnValue + "'",
						(rs, rowNum) -> new Company(rs.getInt("ID"), rs.getString("NAME")
								, rs.getString("CODE"),rs.getString("DBSCHEMA")));
	}

	@Override
	public Company findById(Integer id,Context ctx) {
		return jdbcTemplate
				.queryForObject("SELECT ID,NAME,DESCRIPTION,CODE,OFFICE_PHONE,OWNER_PHONE,DBSCHEMA,STATUS,"
						+ "CREATE_DATE,UPDATED_DATE,UPDATED_BY,ADDRESS,ADDRESS_1,ADDRESS_2,CITY,STATE,"
						+ "LATITUDE,LONGITUDE,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,LINKEDIN,WHATSAPP,"
						+ "FACEBOOK,PARA1,PARA_2,PARA_3,IMG_PATH1,IMG_PATH2,IMG_PATH3 FROM "
						+ "ELOKEVASDB.COMPANY;  WHERE ID = ?",
						new Object[] { id },
						(rs, rowNum) -> new Company(rs.getInt("ID"), rs.getString("NAME")
								,rs.getString("DESCRIPTION"),rs.getString("CODE")
								,rs.getString("ADDRESS"),rs.getString("OFFICE_PHONE"),rs.getString("OWNER_PHONE")
								,rs.getString("DBSCHEMA"),rs.getString("STATUS")
								,rs.getDate("CREATE_DATE"),rs.getDate("UPDATED_DATE")
								,rs.getInt("UPDATED_BY"),rs.getString("ADDRESS_1"),rs.getString("ADDRESS_2")
								,rs.getString("CITY"),rs.getString("STATE")
								,rs.getString("LATITUDE"),rs.getString("LONGITUDE")
								,rs.getString("EMAIL"),rs.getString("FIRST_NAME")
								,rs.getString("LAST_NAME"),rs.getString("PASSWORD")
								,rs.getString("LINKEDIN"),rs.getString("FACEBOOK")
								,rs.getString("WHATSAPP"),rs.getString("PARA1")
								,rs.getString("PARA_2"),rs.getString("PARA_3")
								,rs.getString("IMG_PATH1"),rs.getString("IMG_PATH2")
								,rs.getString("IMG_PATH3")
								));
	}
}