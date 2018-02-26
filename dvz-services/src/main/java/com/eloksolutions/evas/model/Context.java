package com.eloksolutions.evas.model;

public class Context {
	Integer userId;
	String schema;
	
	public Context(Integer userId, String schema) {
		super();
		this.userId = userId;
		this.schema = schema;
	}
	public Integer getUserId() {
		return userId;
	}
	public String getSchema() {
		return schema;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	@Override
	public String toString() {
		return "Context [userId=" + userId + ", schema=" + schema + "]";
	}
	
}
