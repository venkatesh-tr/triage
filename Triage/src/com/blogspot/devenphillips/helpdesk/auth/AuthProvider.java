package com.blogspot.devenphillips.helpdesk.auth;

import java.util.Properties;

public interface AuthProvider {

	/**
	 * Should take the username and password and test the underlying authentication provider for successful authentication
	 * @param username The user's ID
	 * @param password The user's password
	 * @return <code>true</code> if the user is authenticated and otherwise return <code>false</code>
	 */
	public abstract boolean authenticate(String username, String password) throws AuthException ;

	/**
	 * Return the display friendly name for the given user ID
	 * @param username The user's ID
	 * @return A {@link String} containing the display friendly name for the given user ID
	 */
	public abstract String getDisplayName(String username) throws AuthException ;

	/**
	 * Test to see if the given user is a member of the given group
	 * @param username The user's ID
	 * @param group The group to check for membership
	 * @return <code>true</code> if the user is a member of the given group, otherwise <code>false</code>
	 */
	public abstract boolean isUserInGroup(String username, String group) throws AuthException ;

	/**
	 * Retrieve a list of all groups the user is a member of
	 * @param username The user's ID
	 * @return A array of {@link String} where each element is a group that the given user is a member of
	 */
	public abstract String[] getUserGroups(String username) throws AuthException ;

	/**
	 * Test the authentication configuration settings. Used during initial application configuration.
	 * @param config A {@link Properties} object containing the required settings to make this authentication provider functional
	 * @return <code>true</code> if the authentication provider functions with the given settings, otherwise <code>false</code>
	 */
	public abstract boolean testProvider(Properties config) throws AuthException ;
}
