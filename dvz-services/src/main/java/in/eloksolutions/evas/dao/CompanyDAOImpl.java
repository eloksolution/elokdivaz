package in.eloksolutions.evas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;

import in.eloksolutions.evas.model.Company;
import in.eloksolutions.evas.model.Context;

@Repository("companyDAO")
public class CompanyDAOImpl implements CompanyDAO{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	@Override
	public Integer add(Company company,Context  ctx) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				 PreparedStatement statement = con.prepareStatement("INSERT INTO elokevasdb.company (NAME, DESCRIPTION, CODE, OFFICE_PHONE, OWNER_PHONE, "
						+ "DBSCHEMA, STATUS,CREATE_DATE, UPDATED_DATE, UPDATED_BY, ADDRESS, ADDRESS_1, ADDRESS_2,"
						+ "CITY, STATE, LATITUDE,LONGITUDE, EMAIL, FIRST_NAME, LAST_NAME, PASSWORD, LINKEDIN,"
						+ " WHATSAPP, FACEBOOK, PARA1, PARA_2,PARA_3, IMG_PATH1, IMG_PATH2, IMG_PATH3) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
						+ "?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
			        statement.setString(1, company.getName());
			        statement.setString(2, company.getDescriptioin());
			        statement.setString(3, company.getCode());
			        statement.setString(4, company.getOfficePhone());
			        statement.setString(5, company.getOwnerPhone());
			        statement.setString(6, company.getSchema());
			        statement.setString(7, company.getStatus());
			        statement.setDate(8, company.getCreateDate());
			        statement.setDate(9, company.getUpdatedDate());
			        statement.setInt(10, company.getUpdatedBy());
			        statement.setString(11, company.getAddress());
			        statement.setString(12, company.getAddress_1());
			        statement.setString(13, company.getAddress_2());
			        statement.setString(14, company.getCity());
			        statement.setString(15, company.getState());
			        statement.setString(16, company.getLatitude());
			        statement.setString(17, company.getLongitude());
			        statement.setString(18, company.getEmail());
			        statement.setString(19, company.getFirstName());
			        statement.setString(20, company.getLastName());
			        statement.setString(21, company.getPassword());
			        statement.setString(22, company.getLinkedin());
			        statement.setString(23, company.getWhatsapp());
			        statement.setString(24, company.getFacebook());
			        statement.setString(25, company.getPara1());
			        statement.setString(26, company.getPara2());
			        statement.setString(27, company.getPara3());
			        statement.setString(28, company.getImgPath1());
			        statement.setString(29, company.getImgPath2());
			        statement.setString(30, company.getImgPath3());
			        return statement;
			}
		},holder);
	
		return holder.getKey().intValue();
	}
	
	@Override
	public List<Company> findNext(Integer noOfRecords,Context  ctx) {
		return jdbcTemplate
				.query("SELECT ID,NAME,CODE,DBSCHEMA,DESCRIPTION,OFFICE_PHONE,"
						+ "OWNER_PHONE,STATUS,CREATE_DATE,UPDATED_DATE,UPDATED_BY,ADDRESS,ADDRESS_1,"
						+ "ADDRESS_2,CITY,STATE,LATITUDE,LONGITUDE,EMAIL,FIRST_NAME,LAST_NAME,"
						+ "PASSWORD,LINKEDIN,WHATSAPP,FACEBOOK,PARA1,PARA_2,PARA_3,IMG_PATH1,"
						+ "IMG_PATH2,IMG_PATH3 FROM elokevasdb.company WHERE STATUS='Y'"
						+ " LIMIT 10 OFFSET ?",
						new Object[] { noOfRecords },
						(rs, rowNum) -> new Company(rs.getInt("ID"), rs.getString("NAME")
								,rs.getString("CODE"),rs.getString("DBSCHEMA"),rs.getString("DESCRIPTION"),rs.getString("OFFICE_PHONE"),rs.getString("OWNER_PHONE"),rs.getString("STATUS"),rs.getString("CREATE_DATE"),
								rs.getString("UPDATED_DATE"),rs.getInt("UPDATED_BY"),rs.getString("ADDRESS"),rs.getString("ADDRESS_1")
								,rs.getString("ADDRESS_2"),rs.getString("CITY"),rs.getString("STATE"),rs.getString("LATITUDE")
								,rs.getString("LONGITUDE"),rs.getString("EMAIL"),rs.getString("FIRST_NAME"),rs.getString("LAST_NAME")
								,rs.getString("PASSWORD"),rs.getString("LINKEDIN"),rs.getString("WHATSAPP"),rs.getString("FACEBOOK")
								,rs.getString("PARA1"),rs.getString("PARA_2"),rs.getString("PARA_3"),rs.getString("IMG_PATH1")
								,rs.getString("IMG_PATH2"),rs.getString("IMG_PATH3")));
	}
	
	@Override
	public Integer update(Company company,Context  ctx) {
		return jdbcTemplate
				.update("UPDATE elokevasdb.company SET NAME = ?, DESCRIPTION = ?, CODE = ?, OFFICE_PHONE = ?,"
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
				.query("SELECT ID,NAME,CODE,DBSCHEMA,DESCRIPTION,OFFICE_PHONE,OWNER_PHONE,STATUS,CREATE_DATE,UPDATED_DATE,UPDATED_BY,ADDRESS,ADDRESS_1,ADDRESS_2,CITY,STATE,LATITUDE,LONGITUDE,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,LINKEDIN,WHATSAPP,FACEBOOK,PARA1,PARA_2,PARA_3,IMG_PATH1,IMG_PATH2,IMG_PATH3 FROM elokevasdb.company WHERE STATUS='Y' ",
						(rs, rowNum) -> new Company(rs.getInt("ID"), rs.getString("NAME")
								, rs.getString("CODE"),rs.getString("DBSCHEMA"),rs.getString("DESCRIPTION"),rs.getString("OFFICE_PHONE"),rs.getString("OWNER_PHONE"),rs.getString("STATUS"),rs.getString("CREATE_DATE"),
								rs.getString("UPDATED_DATE"),rs.getInt("UPDATED_BY"),rs.getString("ADDRESS"),rs.getString("ADDRESS_1")
								,rs.getString("ADDRESS_2"),rs.getString("CITY"),rs.getString("STATE"),rs.getString("LATITUDE")
								,rs.getString("LONGITUDE"),rs.getString("EMAIL"),rs.getString("FIRST_NAME"),rs.getString("LAST_NAME")
								,rs.getString("PASSWORD"),rs.getString("LINKEDIN"),rs.getString("WHATSAPP"),rs.getString("FACEBOOK")
								,rs.getString("PARA1"),rs.getString("PARA_2"),rs.getString("PARA_3"),rs.getString("IMG_PATH1")
								,rs.getString("IMG_PATH2"),rs.getString("IMG_PATH3")));
	}

	@Override
	public List<Company> findByColumn(String columnName, String columnValue,Context ctx) {
		return jdbcTemplate
				.query("SELECT ID,NAME,CODE,DBSCHEMA,DESCRIPTION,OFFICE_PHONE,OWNER_PHONE,STATUS,CREATE_DATE,UPDATED_DATE,UPDATED_BY,ADDRESS,ADDRESS_1,ADDRESS_2,CITY,STATE,LATITUDE,LONGITUDE,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,LINKEDIN,WHATSAPP,FACEBOOK,PARA1,PARA_2,PARA_3,IMG_PATH1,IMG_PATH2,IMG_PATH3 FROM elokevasdb.company WHERE STATUS='Y' AND "
						+ columnName + "='" + columnValue + "'",
						(rs, rowNum) -> new Company(rs.getInt("ID"), rs.getString("NAME")
								, rs.getString("CODE"),rs.getString("DBSCHEMA"),rs.getString("DESCRIPTION"),rs.getString("OFFICE_PHONE"),rs.getString("OWNER_PHONE"),rs.getString("STATUS"),rs.getString("CREATE_DATE"),
								rs.getString("UPDATED_DATE"),rs.getInt("UPDATED_BY"),rs.getString("ADDRESS"),rs.getString("ADDRESS_1")
								,rs.getString("ADDRESS_2"),rs.getString("CITY"),rs.getString("STATE"),rs.getString("LATITUDE")
								,rs.getString("LONGITUDE"),rs.getString("EMAIL"),rs.getString("FIRST_NAME"),rs.getString("LAST_NAME")
								,rs.getString("PASSWORD"),rs.getString("LINKEDIN"),rs.getString("WHATSAPP"),rs.getString("FACEBOOK")
								,rs.getString("PARA1"),rs.getString("PARA_2"),rs.getString("PARA_3"),rs.getString("IMG_PATH1")
								,rs.getString("IMG_PATH2"),rs.getString("IMG_PATH3")));
	}

	@Override
	public Company findById(Integer id,Context ctx) {
		return jdbcTemplate
				.queryForObject("SELECT ID,NAME,DESCRIPTION,CODE,OFFICE_PHONE,OWNER_PHONE,DBSCHEMA,STATUS,"
						+ "CREATE_DATE,UPDATED_DATE,UPDATED_BY,ADDRESS,ADDRESS_1,ADDRESS_2,CITY,STATE,"
						+ "LATITUDE,LONGITUDE,EMAIL,FIRST_NAME,LAST_NAME,PASSWORD,LINKEDIN,WHATSAPP,"
						+ "FACEBOOK,PARA1,PARA_2,PARA_3,IMG_PATH1,IMG_PATH2,IMG_PATH3 FROM "
						+ "elokevasdb.company;  WHERE ID = ?",
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