package com.blogspot.devenphillips.helpdesk.data;

import java.io.Serializable ;
import java.util.Date;
import java.util.Set;
import javax.persistence.* ;

import org.hibernate.search.annotations.* ;

@Entity
@Indexed
@SequenceGenerator(name="request_seq",sequenceName="request_id_seq")
@Table(name="requests")
public class Request implements Serializable {

	private static final long serialVersionUID = -1624450865428872257L;

	@Id
	@DocumentId
	@GeneratedValue(generator="request_seq",strategy=GenerationType.SEQUENCE)
	private int id = 0 ;
	
	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String subject = null ;
	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String details = null ;
	private String requestor = null ;
	private int priority = 10 ;
	private Date requested = new Date() ;
	private Date completed = null ;
	private String assigned = null ;
	private String approver = null ;
	private Date approved = null ;
	private int estimate = 0 ;

	@OneToMany( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@JoinColumn(name="id")
	@IndexedEmbedded
	private Set<RequestNote> requestNotes = null ;

	/**
	 * Returns the unique id number of the current request object
	 * @return An int containing the unique record index number for the current request
	 */
	public int getId() {
		return id;
	}

	/**
	 * Sets the unique id number for the current request. Should not ever be used becuase it is automatically generated
	 * @param id An int to be used as the unique record index number for this request
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Returns the short description of this request.
	 * @return A {@link String} object containing the short description of the current request
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * Sets the short description of the current request
	 * @param subject A {@link String} object which contains the short description of the current request
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * Returns a {@link String} object containing the long description of the current request
	 * @return A {@link String} object containing the long description of the current request
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * Sets the long description of the current request
	 * @param details A {@link String} object containing the long description of the current request.
	 */
	public void setDetails(String details) {
		this.details = details;
	}
	
	public String getRequestor() {
		return requestor;
	}
	
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	
	public int getPriority() {
		return priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
	
	public Date getRequested() {
		return requested;
	}
	
	public void setRequested(Date requested) {
		this.requested = requested;
	}
	
	public Date getCompleted() {
		return completed;
	}
	
	public void setCompleted(Date completed) {
		this.completed = completed;
	}
	
	public String getAssigned() {
		return assigned;
	}
	
	public void setAssigned(String assigned) {
		this.assigned = assigned;
	}
	
	public String getApprover() {
		return approver;
	}
	
	public void setApprover(String approver) {
		this.approver = approver;
	}
	
	public Date getApproved() {
		return approved;
	}
	
	public void setApproved(Date approved) {
		this.approved = approved;
	}

	/**
	 * Get the number of minutes estimated to complete the request
	 * @return int Minutes claimed for the estimate
	 */
	public int getEstimate() {
		return estimate;
	}

	/**
	 * Set the number of minutes estimated to complete the request
	 * @param estimate An int representing the number of minutes estimated to complete the request
	 */
	public void setEstimate(int estimate) {
		this.estimate = estimate;
	}

	/**
	 * Get the notes associated with this request. There should be on note per period of time worked
	 * @return A Collection of {@link RequestNote} objects, each representing a period of work
	 */
	public Set<RequestNote> getRequestNotes() {
		return requestNotes;
	}

	/**
	 * Stores all request notes. Should not ever be used, the program should always store individual notes.
	 * @param requestNotes A Collection of {@link RequestNote} objects
	 */
	public void setRequestNotes(Set<RequestNote> requestNotes) {
		this.requestNotes = requestNotes;
	}
}