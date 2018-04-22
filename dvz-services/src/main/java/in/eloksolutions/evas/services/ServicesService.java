package in.eloksolutions.evas.services;

import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Service;

public interface ServicesService extends CommonService<Service>{
	public Integer updateRating(Service model,Context  ctx) ;
}
