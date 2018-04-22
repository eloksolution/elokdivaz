package in.eloksolutions.evas.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import in.eloksolutions.evas.dao.PackageItemDAO;
import in.eloksolutions.evas.model.Context;
import in.eloksolutions.evas.model.PackageItem;
@Repository("packageItemService")
public class PackageItemServicesImpl implements PackageItemServices{
@Autowired
PackageItemDAO packageItemDAO;
	@Override
	public Integer add(PackageItem packageItem, Context ctx) throws Exception {
		return packageItemDAO.add(packageItem, ctx);
	}

	@Override
	public List<PackageItem> findNext(Integer noOfRecords, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PackageItem> findAll(Context ctx) {
		
		return packageItemDAO.findAll(ctx);
	}

	@Override
	public PackageItem findById(Integer id, Context ctx) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer update(PackageItem model, Context ctx) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
