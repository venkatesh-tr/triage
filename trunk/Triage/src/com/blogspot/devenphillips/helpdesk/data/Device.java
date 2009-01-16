package com.blogspot.devenphillips.helpdesk.data;

import javax.persistence.* ;
import org.hibernate.search.annotations.* ;

@Entity
@Table(name="devices")
@SequenceGenerator(name="device_seq", sequenceName="devices_id_seq")
public class Device implements java.io.Serializable {

	private static final long serialVersionUID = 6919333457394354053L;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String name = null ;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String manufacturer = null ;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String model = null ;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String serial = null ;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String notes = null ;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String location = null ;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="device_seq")
	private int id = 0 ;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getId() {
		return id;
	}

}
