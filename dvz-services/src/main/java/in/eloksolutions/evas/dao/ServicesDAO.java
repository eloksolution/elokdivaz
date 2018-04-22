package in.eloksolutions.evas.dao;

import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Service;

public interface ServicesDAO extends CommonDAO<Service>{
	public Integer updateRating(Service model,Context  ctx) ;
}
