package com.blogspot.devenphillips.helpdesk.data;

import java.io.Serializable;
import javax.persistence.* ;
import org.hibernate.search.annotations.* ;
import java.util.Date ;

@Entity
@SequenceGenerator(name="requestnote_id",sequenceName="requestnote_id_seq")
@Table(name="requestnotes")
public class RequestNote implements Serializable {

	private static final long serialVersionUID = 3156124638594068441L;

	@Id
	@GeneratedValue(generator="requestnote_id",strategy=GenerationType.SEQUENCE)
	private int id = 0 ;

	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String content = null ;
	private Date added = new Date() ;
	private String technician = null ;
	private int time = 0 ;
	private int request = 0 ;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getAdded() {
		return added;
	}
	
	public void setAdded(Date added) {
		this.added = added;
	}
	
	public String getTechnician() {
		return technician;
	}
	
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	
	public int getTime() {
		return time;
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getRequest() {
		return request;
	}
	
	public void setRequest(int request) {
		this.request = request;
	}
}