package com.blogspot.devenphillips.helpdesk.data;

import java.util.Date;
import javax.persistence.* ;
import org.hibernate.search.annotations.* ;

@Entity
@Table(name="ticketnotes")
@SequenceGenerator(name="ticketnote_id",sequenceName="ticketnotes_id_seq")
public class TicketNote implements java.io.Serializable {

	private static final long serialVersionUID = 3677675182116770882L;

	@Id
	@GeneratedValue(generator="ticketnote_id",strategy=GenerationType.SEQUENCE)
	private int id = 0 ;

	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String content = null ;
	private Date added = new Date() ;
	private String technician = null ;
	private int worktime = 0 ;
	private int ticket = 0 ;

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
	
	public int getWorktime() {
		return worktime;
	}
	
	public void setWorktime(int worktime) {
		this.worktime = worktime;
	}

	public int getTicket() {
		return ticket;
	}

	public void setTicket(int ticket) {
		this.ticket = ticket;
	}
}
