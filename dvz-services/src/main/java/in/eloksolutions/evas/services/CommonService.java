package in.eloksolutions.evas.services;

import java.util.List;

import in.eloksolutions.evas.model.Context;


public interface CommonService<T> {
	 public Integer add(T company,Context ctx) throws Exception;;
	 public List<T> findNext(Integer noOfRecords,Context ctx) ;
	 public List<T> findAll(Context ctx) ;
	 public T findById(Integer id,Context ctx) ;
	 public Integer update(T model,Context ctx) throws Exception;
}
