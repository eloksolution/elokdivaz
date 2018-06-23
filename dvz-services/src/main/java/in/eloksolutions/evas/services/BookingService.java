package in.eloksolutions.evas.services;

import java.util.List;

import in.eloksolutions.evas.model.Booking;
import in.eloksolutions.evas.model.Context;

public interface BookingService extends CommonService<Booking>{
	public List<Booking> myBookings(Integer custId, Context ctx);
}
