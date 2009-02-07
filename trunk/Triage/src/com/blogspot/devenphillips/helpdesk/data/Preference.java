package com.blogspot.devenphillips.helpdesk.data;

import java.io.Serializable;

import javax.persistence.* ;

@Entity
@Table(name="user_prefs")
@SequenceGenerator(name="prefs_seq", sequenceName="prefs_id_seq")
public class Preference implements Serializable {

	private static final long serialVersionUID = 8999088078104503632L;

	@Id
	@GeneratedValue(generator="prefs_seq",strategy=GenerationType.SEQUENCE)
	private int prefId = 0 ;
	private String userId = null ;
	private String preference = null ;
	private String value = null ;

	public void setPrefId(int prefId) {
		this.prefId = prefId;
	}

	public int getPrefId() {
		return prefId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPreference() {
		return preference;
	}

	public void setPreference(String preference) {
		this.preference = preference;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}