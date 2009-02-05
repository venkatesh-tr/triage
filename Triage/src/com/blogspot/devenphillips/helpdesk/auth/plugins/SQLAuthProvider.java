package com.blogspot.devenphillips.helpdesk.auth.plugins;

import com.blogspot.devenphillips.helpdesk.auth.AuthException;
import com.blogspot.devenphillips.helpdesk.auth.AuthProvider;

public class SQLAuthProvider implements AuthProvider {

	public boolean authenticate(String username, String password)
			throws AuthException {
		// TODO Auto-generated method stub
		return false;
	}

	public String getDisplayName(String username) throws AuthException {
		// TODO Auto-generated method stub
		return null;
	}

	public String[] getUserGroups(String username) throws AuthException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isUserInGroup(String username, String group)
			throws AuthException {
		// TODO Auto-generated method stub
		return false;
	}

}
