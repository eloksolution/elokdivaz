package in.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.dao.BookingsDAO;
import in.eloksolutions.evas.dao.CompanyCustomerDAO;
import in.eloksolutions.evas.model.Booking;
import in.eloksolutions.evas.model.CompanyCustomer;
import in.eloksolutions.evas.model.Context;

@Repository("bookingService")
public class BookingServiceImpl implements BookingService {
	@Autowired
    private BookingsDAO bookingDAO;
	@Autowired
    private CompanyCustomerDAO companyCustomerDAO;
	@Autowired
    private CustomerService customerService;
	
	@Override
	public Integer add(Booking booking, Context ctx) throws Exception {
		Integer id=bookingDAO.add(booking, ctx);
		System.out.println(" booking id "+id+" booking "+booking+" context "+ctx+" companyCustomerDAO "+companyCustomerDAO);
		if(id>0){
			try {
				CompanyCustomer cc=new CompanyCustomer(0, booking.getCustomerId(), booking.getCustomerName(), ctx.getCompanyId(), booking.getCompanyName());
				companyCustomerDAO.add(cc, ctx);
				customerService.updateMyRecentParlours( booking.getCustomerId(), ctx.getCompanyId(), booking.getCompanyName(),ctx);
				System.out.println("Updated company customer and my parlours");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return id;
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

	@Override
	public List<Booking> myBookings(Integer custId, Context ctx) {
		return bookingDAO.myBookings(custId, ctx);
	}

}
