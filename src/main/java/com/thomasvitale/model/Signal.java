package com.thomasvitale.model;

import java.util.Date;

public class Signal {

	private String id;
	
	private String author;
	
	private String address;
	
	private String description;
	
	private Date origin;

	private Date lastAccess;
	
	private double average;
	
	public Signal() {
	}

	public Signal(String id, String author, String address, String description, Date origin, Date lastAccess,
			double average) {
		super();
		this.id = id;
		this.author = author;
		this.address = address;
		this.description = description;
		this.origin = origin;
		this.lastAccess = lastAccess;
		this.average = average;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getOrigin() {
		return origin;
	}

	public void setOrigin(Date origin) {
		this.origin = origin;
	}

	public Date getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(Date lastAccess) {
		this.lastAccess = lastAccess;
	}

	public double getAverage() {
		return average;
	}

	public void setAverage(double average) {
		this.average = average;
	}	
	
}
