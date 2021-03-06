package in.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.dao.ServicesDAO;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Service;

@Repository("servicesService")
public class ServicesServiceImpl implements ServicesService {
	@Autowired
    private ServicesDAO serviceDAO;
	@Override
	public Integer add(Service service, Context ctx) throws Exception {
		return serviceDAO.add(service, ctx);
	}

	@Override
	public List<Service> findNext(Integer noOfRecords, Context ctx) {
		return serviceDAO.findNext(noOfRecords, ctx);
	}

	@Override
	public List<Service> findAll(Context ctx) {
		return serviceDAO.findAll(ctx);
	}

	@Override
	public Service findById(Integer id, Context ctx) {
		Service s=null;
		try {
			s= serviceDAO.findById(id, ctx);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error while fetching the records");
		}
		return s;
	}

	@Override
	public Integer update(Service model, Context ctx) throws Exception {
		return serviceDAO.update(model, ctx);
	}

	@Override
	public Integer updateRating(Service model, Context ctx) {
		return serviceDAO.updateRating(model, ctx);
	}

}
