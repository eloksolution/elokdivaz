package com.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.dao.PackageDAO;
import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Packages;
@Repository("packageService")
public class PackageServiceImpl implements PackageService {
@Autowired
private PackageDAO packageDAO;
	@Override
	public Integer add(Packages packages, Context ctx) throws Exception {
		// TODO Auto-generated method stub
		return packageDAO.add(packages, ctx);
	}

	@Override
	public List<Packages> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Packages> findAll(Context ctx) {
		
		return packageDAO.findAll(ctx);
	}

	@Override
	public Packages findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Packages model, Context ctx) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	

}
