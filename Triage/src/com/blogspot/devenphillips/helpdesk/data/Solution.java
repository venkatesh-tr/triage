package com.blogspot.devenphillips.helpdesk.data;

import java.io.Serializable;
import java.util.Date;
import org.hibernate.search.annotations.* ;
import javax.persistence.* ;

@Entity
@SequenceGenerator(name="solution_id",sequenceName="solution_id_seq")
@Table(name="solutions")
public class Solution implements Serializable {

	private static final long serialVersionUID = 3760604102193233720L;

	@Id
	@GeneratedValue(generator="solution_id",strategy=GenerationType.SEQUENCE)
	private int id = 0 ;

	@Field(index=Index.TOKENIZED,store=Store.NO)
	private String content = null ;
	private String technician = null ;
	private Date solved = null ;

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
	
	public String getTechnician() {
		return technician;
	}
	
	public void setTechnician(String technician) {
		this.technician = technician;
	}
	
	public Date getSolved() {
		return solved;
	}
	
	public void setSolved(Date solved) {
		this.solved = solved;
	}
}