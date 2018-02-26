package com.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.eloksolutions.evas.dao.BookingsDAO;
import com.eloksolutions.evas.model.Booking;
import com.eloksolutions.evas.model.Context;

@Repository("bookingService")
public class BookingServiceImpl implements BookingService {
	@Autowired
    private BookingsDAO bookingDAO;
	@Override
	public Integer add(Booking booking, Context ctx) throws Exception {
		return bookingDAO.add(booking, ctx);
	}

	@Override
	public List<Booking> findNext(Integer noOfRecords, Context ctx) {
		return bookingDAO.findNext(noOfRecords, ctx);
	}

	@Override
	public List<Booking> findAll(Context ctx) {
		return bookingDAO.findAll(ctx);
	}

	@Override
	public Booking findById(Integer id, Context ctx) {
		return bookingDAO.findById(id, ctx);
	}

	@Override
	public Integer update(Booking model, Context ctx) throws Exception {
		return bookingDAO.update(model, ctx);
	}

}
