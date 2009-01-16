package com.blogspot.devenphillips.helpdesk.data;

import java.io.Serializable;
import javax.persistence.* ;
import org.hibernate.search.annotations.* ;

@Entity
@SequenceGenerator(name="software_seq",sequenceName="software_id_seq")
@Table(name="software")
public class Software implements Serializable {

	private static final long serialVersionUID = -6476197388706579276L;

	@Id
	@GeneratedValue(generator="software_seq",strategy=GenerationType.SEQUENCE)
	private int id = 0 ;

	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String name = null ;
	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String manufacturer = null ;
	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String version = null ;
	private String description = null ;

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
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
	
	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}