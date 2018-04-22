package in.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.dao.GalleryDAO;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.Gallary;
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
		return galleryDAO.findNext(noOfRecords, ctx);
	}

	@Override
	public List<Gallary> findAll(Context ctx) {
		
		return galleryDAO.findAll(ctx);
	}

	@Override
	public Gallary findById(Integer id, Context ctx) {
		return galleryDAO.findById(id, ctx);
	}

	@Override
	public Integer update(Gallary model, Context ctx) throws Exception {
		return galleryDAO.update(model, ctx);
	}

}
