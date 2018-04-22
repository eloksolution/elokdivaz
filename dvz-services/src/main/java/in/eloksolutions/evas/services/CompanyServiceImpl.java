package in.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.dao.CompanyDAO;
import in.eloksolutions.evas.model.Company;
import in.eloksolutions.evas.model.Context;

@Repository("companyService")
public class CompanyServiceImpl implements CompanyService{
	@Autowired
    private CompanyDAO companyDAO;

	@Override
	public Integer add(Company company,Context ctx) {
		String dbSchema="dbName";
		company.setSchema(dbSchema);
		Integer id= companyDAO.add(company,ctx);
	
		return id;
	}

	@Override
	public List<Company> findNext(Integer noOfRecords,Context ctx) {
		return companyDAO.findNext(noOfRecords,ctx);
	}

	@Override
	public List<Company> findAll(Context ctx) {
		return companyDAO.findAll(ctx);
	}

	@Override
	public Integer update(Company company,Context ctx) throws Exception {
		return companyDAO.update(company, ctx);
	}

	@Override
	public boolean isCompanyCodeExist(String code) {
		return !companyDAO.findByColumn("CODE", code, null).isEmpty();
	}

	@Override
	public Company findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}
}
