package com.eloksolutions.evas.services;

import com.eloksolutions.evas.model.Company;


public interface CompanyService extends CommonService<Company>{
	
	boolean isCompanyCodeExist(String code);
	
}
