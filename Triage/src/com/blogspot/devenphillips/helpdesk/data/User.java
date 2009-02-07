package com.blogspot.devenphillips.helpdesk.data;

import java.util.Set;

import javax.persistence.* ;

import org.hibernate.search.annotations.* ;

@Entity
@Table(name="users")
@Indexed
@SequenceGenerator(name="user_seq", sequenceName="user_id_seq")
public class User implements java.io.Serializable {

	private static final long serialVersionUID = -3946375223199150445L;

	@Id
	@DocumentId
	@GeneratedValue(generator="user_seq",strategy=GenerationType.SEQUENCE)
	private int userId = 0 ;

	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String forename = null ;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String surname = null ;
	@Field(index=Index.TOKENIZED, store=Store.NO)
	private String notes = null ;
	private String workphone = null ;
	private String mobile = null ;
	private String email = null ;
	private String password = null ;

	@ManyToMany( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@JoinTable(name="usergroups",joinColumns={@JoinColumn(name="userId",unique=false)},inverseJoinColumns={@JoinColumn(name="groupId",unique=false)})
	@IndexedEmbedded
	private Set<Group> groups = null ;

	@OneToMany( cascade = { CascadeType.PERSIST, CascadeType.REMOVE } )
	@JoinColumn(name="userId")
	private Set<Preference> preferences = null ;

	public String getForename() {
		return forename;
	}

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getWorkphone() {
		return workphone;
	}

	public void setWorkphone(String workphone) {
		this.workphone = workphone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getRecord() {
		return userId;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean checkPassword(String password) {
		// TODO: Check encrypted password VS database password.
		return true ;
	}

	public void setPreferences(Set<Preference> preferences) {
		this.preferences = preferences;
	}

	public Set<Preference> getPreferences() {
		return preferences;
	}
}
