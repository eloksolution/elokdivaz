package com.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.dao.GalleryDAO;
import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Gallary;
@Repository("galleryService")
public class GalleryServiceImpl implements GalleryService {
	@Autowired
    private GalleryDAO galleryDAO;
	@Override
	public Integer add(Gallary gallary, Context ctx) throws Exception {
		
		return galleryDAO.add(gallary, ctx);
	}

	@Override
	public List<Gallary> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Gallary> findAll(Context ctx) {
		
		return galleryDAO.findAll(ctx);
	}

	@Override
	public Gallary findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(Gallary model, Context ctx) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}