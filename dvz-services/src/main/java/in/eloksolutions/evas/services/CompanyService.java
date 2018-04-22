package in.eloksolutions.evas.services;

import in.eloksolutions.evas.model.Company;


public interface CompanyService extends CommonService<Company>{
	
	boolean isCompanyCodeExist(String code);
	
}
