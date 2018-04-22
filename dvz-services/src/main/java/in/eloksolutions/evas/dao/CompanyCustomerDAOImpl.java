package in.eloksolutions.evas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.mysql.jdbc.Statement;

import in.eloksolutions.evas.model.Company;
import in.eloksolutions.evas.model.CompanyCustomer;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Customer;
import in.eloksolutions.evas.util.ParlourFormattor;

@Repository("companyCustomerDAO")
public class CompanyCustomerDAOImpl implements CompanyCustomerDAO{
	@Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<CompanyCustomer> findAll(Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyCustomer> findByColumn(String columnName, String columnValue, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CompanyCustomer> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompanyCustomer findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer add(CompanyCustomer model, Context ctx) {
		GeneratedKeyHolder holder = new GeneratedKeyHolder();
		try {
			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					 PreparedStatement statement = con.prepareStatement("INSERT INTO elokevasdb.company_customers "
					 		+ "(customer_id, customer_name, company_id, company_name) "
							+ "VALUES (?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);
				        statement.setInt(1, model.getCustomerId());
				        statement.setString(2, model.getCustomerName());
				        statement.setInt(3, model.getCompanyId());
				        statement.setString(4, model.getCompanyName());
				        
				        return statement;
				}
			},holder);
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return holder.getKey().intValue();
	}

	@Override
	public Integer update(CompanyCustomer model, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer delete(CompanyCustomer model, Context ctx) {
		return jdbcTemplate
				.update("DELETE FROM elokevasdb.company_customers WHERE COMPANY_ID=? AND CUSTOMER_ID=?",
						new Object[] { model.getCompanyId(),
								model.getCustomerId()});
	}

	@Override
	public List<Customer> getCustomers(Context ctx) {
		return jdbcTemplate.query(
		        "SELECT CU.* FROM elokevasdb.company_customers CC,elokevasdb.customers CU WHERE "
		        + "CC.COMPANY_ID=? " ,
				new Object[] { ctx.getCompanyId() },
		        (rs, rowNum) -> new Customer(rs.getInt("id"),
		                rs.getString("FIRST_NAME"), rs.getString("LAST_NAME"), rs.getString("EMAIL"),
		                rs.getString("ADDRESS_1"), rs.getString("ADDRESS_2"),
		                rs.getString("CITY"), rs.getString("STATE"),ParlourFormattor.parse(rs.getString("MY_PARALORS")),
		                rs.getDate("CREATE_DATE"),rs.getDate("UPDATE_DATE"),rs.getString("LATITUDE"), rs.getString("LONGITUDE"),
		                rs.getString("PHONE"), rs.getString("IMAGE_PATH"), rs.getString("DEVICE_TOKEN")
		                ));
	}

	@Override
	public List<Company> getCompanies(Context ctx, int customerId) {
		return jdbcTemplate
				.query("SELECT C.* FROM elokevasdb.company_customers C,elokevasdb.customers CU WHERE CUSTOMER_ID=? ",
						(rs, rowNum) -> new Company(rs.getInt("ID"), rs.getString("NAME")
								, rs.getString("CODE"),rs.getString("DBSCHEMA"),rs.getString("DESCRIPTION"),rs.getString("OFFICE_PHONE"),rs.getString("OWNER_PHONE"),rs.getString("STATUS"),rs.getString("CREATE_DATE"),
								rs.getString("UPDATED_DATE"),rs.getInt("UPDATED_BY"),rs.getString("ADDRESS"),rs.getString("ADDRESS_1")
								,rs.getString("ADDRESS_2"),rs.getString("CITY"),rs.getString("STATE"),rs.getString("LATITUDE")
								,rs.getString("LONGITUDE"),rs.getString("EMAIL"),rs.getString("FIRST_NAME"),rs.getString("LAST_NAME")
								,rs.getString("PASSWORD"),rs.getString("LINKEDIN"),rs.getString("WHATSAPP"),rs.getString("FACEBOOK")
								,rs.getString("PARA1"),rs.getString("PARA_2"),rs.getString("PARA_3"),rs.getString("IMG_PATH1")
								,rs.getString("IMG_PATH2"),rs.getString("IMG_PATH3")));
	}

}
