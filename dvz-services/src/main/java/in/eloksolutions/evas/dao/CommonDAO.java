package in.eloksolutions.evas.dao;

import java.util.List;

import in.eloksolutions.evas.model.Context;

public interface CommonDAO<T> {
	public List<T> findAll(Context  ctx) ;
	public List<T> findByColumn(String columnName,String columnValue,Context  ctx) ;
	public List<T> findNext(Integer noOfRecords,Context  ctx) ;
	public T findById(Integer id,Context  ctx) ;
	public Integer add(T model,Context  ctx) ;
	public Integer update(T model,Context  ctx) ;
	public Integer delete(T model,Context  ctx) ;
}
