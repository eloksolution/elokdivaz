package com.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.dao.OffersDAO;
import com.eloksolutions.evas.model.Context;
import com.eloksolutions.evas.model.Offer;
import com.eloksolutions.evas.model.Service;

@Repository("offersService")
public class OffersServiceImpl implements OffersService {
	@Autowired
    private OffersDAO offersDAO;
	@Override
	public Integer add(Offer offer, Context ctx) throws Exception {
		return offersDAO.add(offer, ctx);
	}

	@Override
	public List<Offer> findNext(Integer noOfRecords, Context ctx) {
		return offersDAO.findNext(noOfRecords, ctx);
	}

	@Override
	public List<Offer> findAll(Context ctx) {
		return offersDAO.findAll(ctx);
	}

	@Override
	public Offer findById(Integer id, Context ctx) {
		return offersDAO.findById(id, ctx);
	}

	@Override
	public Integer update(Offer model, Context ctx) throws Exception {
		return offersDAO.update(model, ctx);
	}

}
