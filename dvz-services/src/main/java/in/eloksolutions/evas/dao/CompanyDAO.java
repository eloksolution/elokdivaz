  package in.eloksolutions.evas.dao;

import java.util.List;

import in.eloksolutions.evas.model.Company;
import in.eloksolutions.evas.model.Context;

public interface CompanyDAO extends CommonDAO<Company>{
	public List<Company> findByCodeOrName(String searchToken,Context ctx) ;
}
