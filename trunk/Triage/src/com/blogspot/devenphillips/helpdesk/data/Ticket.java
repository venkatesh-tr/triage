package com.blogspot.devenphillips.helpdesk.data;

import java.util.Date;
import java.util.Set;
import javax.persistence.* ;
import org.hibernate.search.annotations.* ;

@Entity
@Table(name="tickets")
@Indexed
@SequenceGenerator(name="ticket_seq",sequenceName="ticket_id_seq")
public class Ticket implements java.io.Serializable {

	private static final long serialVersionUID = -2832598371033152296L;

	@Id
	@DocumentId
	@GeneratedValue(generator="ticket_seq",strategy=GenerationType.SEQUENCE)
	private int id = 0 ;

	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String subject = null ;
	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String description = null ;
	private Date created = new Date() ;
	private Date closed = null ;
	private String assigned = null ;
	private int priority = 10 ;
	private int severity = 5 ;
	private int status = Ticket.NEW ;

	@OneToMany( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@JoinColumn(name="ticket")
	@IndexedEmbedded
	private Set<TicketNote> notes = null ;

	@ManyToMany( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@JoinTable(name="ticketdevices",joinColumns={@JoinColumn(name="ticket",unique=false)},inverseJoinColumns={@JoinColumn(name="device",unique=false)})
	@IndexedEmbedded
	private Set<Device> devices = null ;

	@ManyToMany( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@JoinTable(name="ticketsoftware",joinColumns={@JoinColumn(name="software",unique=false)},inverseJoinColumns={@JoinColumn(name="ticket",unique=false)})
	@IndexedEmbedded
	private Set<Software> software = null ;

	@OneToOne( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@JoinColumn(name="ticket")
	@IndexedEmbedded
	private Solution solution = null ;

	// Constants which define the current ticket status
	public final static int NEW = 0 ;
	public final static int OPEN = 1 ;
	public final static int IN_PROGRESS = 2 ;
	public final static int TESTING = 3 ;
	public final static int AWAITING_RESPONSE = 4 ;
	public final static int ASSIGNED = 5 ;
	public final static int OVERDUE = 6 ;
	public final static int CLOSED = 100 ;

	/**
	 * A method to return the human readable status message for a given status value
	 * @param status An int which represents the current ticket status as defined in the class constants
	 * @return A {@link String} respresentation of the current ticket status
	 */
	public static String getStatusMsg(int status) {
		String retVal = null ;
		switch (status) {
			case 0 : retVal = "New" ; break ;
			case 1 : retVal = "Open" ; break ;
			case 2 : retVal = "In Progress" ; break ;
			case 3 : retVal = "Testing Solution" ; break ;
			case 4 : retVal = "Waiting for user response" ; break ;
			case 5 : retVal = "Assigned" ; break ;
			case 6 : retVal = "Overdue" ; break ;
			case 100 : retVal = "Closed" ; break ;
		}
		
		return retVal ;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Date getCreated() {
		return created;
	}
	
	public void setCreated(Date created) {
		this.created = created;
	}
	
	public Date getClosed() {
		return closed;
	}
	
	public void setClosed(Date closed) {
		this.closed = closed;
	}
	
	public String getAssigned() {
		return assigned;
	}
	
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getSeverity() {
		return severity;
	}

	public void setSeverity(int severity) {
		this.severity = severity;
	}

	public Set<TicketNote> getNotes() {
		return notes;
	}

	public void setNotes(Set<TicketNote> notes) {
		this.notes = notes;
	}

	public Set<Device> getDevices() {
		return devices;
	}

	public void setDevices(Set<Device> devices) {
		this.devices = devices;
	}

	public Set<Software> getSoftware() {
		return software;
	}

	public void setSoftware(Set<Software> software) {
		this.software = software;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}
}
