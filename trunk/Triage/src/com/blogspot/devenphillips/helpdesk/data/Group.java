package com.blogspot.devenphillips.helpdesk.data;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Indexed;

@Entity
@Table(name="groups")
@Indexed
@SequenceGenerator(name="group_seq", sequenceName="group_id_seq")
public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7289963622724203841L;

	@Id
	@DocumentId
	@GeneratedValue(generator="group_seq",strategy=GenerationType.SEQUENCE)
	private int groupId = 0 ;

	private String name = null ;
	private Date created = null ;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public int getRecord() {
		return groupId;
	}
}
