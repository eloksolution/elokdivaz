package in.eloksolutions.evas.dao;

import java.util.List;

import in.eloksolutions.evas.model.Booking;
import in.eloksolutions.evas.model.Context;

public interface BookingsDAO extends CommonDAO<Booking>{

	List<Booking> myBookings(Integer id, Context ctx);

}
