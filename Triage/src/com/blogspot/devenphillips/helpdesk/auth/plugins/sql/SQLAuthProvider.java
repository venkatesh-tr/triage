package com.blogspot.devenphillips.helpdesk.auth.plugins.sql;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Label;
import org.zkoss.zul.Vbox;
import org.zkoss.zul.Window;
import com.blogspot.devenphillips.helpdesk.auth.AuthException;
import com.blogspot.devenphillips.helpdesk.auth.AuthProvider;
import com.blogspot.devenphillips.helpdesk.auth.support.JCrypt;
import com.blogspot.devenphillips.helpdesk.data.Group;
import com.blogspot.devenphillips.helpdesk.data.User;
import com.blogspot.devenphillips.helpdesk.data.dao.UserDAO;

/**
 * An implementation of AuthProvider which works with users and groups stored
 * in the SQL database.
 * @author <a href="mailto:deven.phillips@gmail.com">Deven Phillips</a>
 *
 */
public class SQLAuthProvider implements AuthProvider {

	/**
	 * Verifies the password versus the database for the given username/password
	 * @param username The username to verify the password for
	 * @param The password to be checked against the value in the database
	 * @return True is the password matches, false otherwise
	 */
	public boolean authenticate(String username, String password) throws AuthException {
		UserDAO dao = new UserDAO() ;
		User currUser = dao.findByUsername(username) ;
		String salt = currUser.getPassword().substring(0,2) ;
		String cryptText = JCrypt.crypt(salt, password) ;
		if (cryptText.compareTo(currUser.getPassword())==0) {
			return true ;
		} else {
			return false;
		}
	}

	/**
	 * Get the display name of the given user
	 * @param username The username to fetch the display name for
	 * @return The full name of the user with the specified username
	 */
	public String getDisplayName(String username) throws AuthException {
		UserDAO dao = new UserDAO() ;
		User currUser = dao.findByUsername(username) ;
		return currUser.getForename()+" "+currUser.getSurname() ;
	}

	/**
	 * Returns a list of groups that the given user is a member of
	 * @param username Username to use to look for groups it is a member of
	 * @return A String array containing the names of all of the groups the user is a member of
	 */
	public String[] getUserGroups(String username) throws AuthException {
		UserDAO dao = new UserDAO() ;
		User currUser = dao.findByUsername(username) ;
		Set<Group> userGroups = currUser.getGroups() ;
		Iterator<Group> groupIter = userGroups.iterator();
		ArrayList<String> groupList = new ArrayList<String>() ;
		while (groupIter.hasNext()) {
			Group currGroup = groupIter.next() ;
			groupList.add(currGroup.getName()) ;
		}
		return (String[])groupList.toArray() ;
	}

	/**
	 * Given a set of user name and group name, determine if the user is in the given group
	 * @param username The Username to be tested
	 * @param group The name of the group to be tested for membership
	 * @return True if the user is in the group, false otherwise.
	 */
	public boolean isUserInGroup(String username, String group) throws AuthException {
		UserDAO dao = new UserDAO() ;
		User currUser = dao.findByUsername(username) ;
		Set<Group> userGroups = currUser.getGroups() ;
		Iterator<Group> groupIter = userGroups.iterator();
		boolean retVal = false ;
		while (groupIter.hasNext()) {
			Group currGroup = groupIter.next() ;
			if (currGroup.getName().compareTo(group)==0) {
				retVal = true ;
			}
		}
		return retVal;
	}

	public static String getModuleShortName() {
		return "SQL" ;
	}

	public static String getModuleName() {
		return "SQL Authentication Provider" ;
	}

	public static String getModuleDescription() {
		return "Allows for authenticating users and pulling user information from an SQL database." ;
	}

	public static String getVersion() {
		return "0.0.1" ;
	}

	/**
	 * Display an object which contains all of the inputs needed to configure the 
	 * SQL Auth Provider module
	 * @return An object which has components and events to configure the SQL Auth Provider module
	 */
	public static Vbox showConfig() {
		java.io.InputStream zulInput = SQLAuthProvider.class.getClassLoader().getResourceAsStream("com/blogspot/devenphillips/helpdesk/auth/plugins/sql/config.zul") ;
		Reader zulReader = new InputStreamReader(zulInput) ;
		Vbox retVal = new Vbox() ;
		try {
			retVal = (Vbox)Executions.createComponentsDirectly(zulReader,"zul",null,new HashMap<String,String>()) ;
		} catch (Exception e) {
			Window errWin = new Window() ;
			errWin.setTitle("Configuration Error") ;
			try {
				errWin.setMode("overlapped") ;
			} catch (InterruptedException e1) {
				// This catch block will never be reached because the window is not modal.
			}
			Label errMessage = new Label("Unable to load configuration interface for SQL.") ;
			errWin.setWidth("400px") ;
			errWin.setHeight("200px") ;
			errWin.appendChild(errMessage) ;
			retVal.appendChild(errWin) ;
		}
		return retVal ;
	}
}
