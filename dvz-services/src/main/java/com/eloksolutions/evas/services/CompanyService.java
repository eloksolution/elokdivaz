package com.eloksolutions.evas.services;

import java.util.List;

import com.eloksolutions.evas.model.Company;
import com.eloksolutions.evas.model.Context;


public interface CompanyService {
	 public Integer add(Company company,Context ctx) throws Exception;;
	 public List<Company> findNext(Integer noOfRecords,Context ctx) ;
	 public List<Company> findAll(Context ctx) ;
	 public Integer update(Company model,Context ctx) throws Exception;
	 public boolean isCompanyCodeExist(String code);
}
