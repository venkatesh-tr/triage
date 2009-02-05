package com.blogspot.devenphillips.helpdesk.auth;

import java.util.Hashtable;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import com.sun.jndi.ldap.LdapCtxFactory;

public class LDAPAuth {

	public static boolean authenticate(String username, String password) {
		boolean isAuthenticated = false ;
		// Set up the environment for creating the initial context
		Hashtable<String, String> env = new Hashtable<String, String>();
		// Authenticate 'username' with 'password' to by attempting to bind to the LDAP server
		env.put(Context.SECURITY_PRINCIPAL, username+"@metalsales.us.com");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_CREDENTIALS, password);

		if (password.length()!=0) {
			// Create the initial context
			try {
				DirContext ctx = LdapCtxFactory.getLdapCtxInstance("ldap://dc1.metalsales.us.com:389/DC=metalsales,DC=us,DC=com", env) ;
				isAuthenticated = true ;
				ctx.close() ;
				ctx = null ;
			} catch (Throwable e) {
				isAuthenticated = false ;
				e.printStackTrace() ;
			}
		}

		return isAuthenticated ;
	}

	public static boolean isAdmin(String username) {
		boolean isAdmin = false ;

		String[] filterArgs = {username} ;
		String filter = "(&(samaccountName={0})(|(memberof=CN=Domain Admins,CN=Users,DC=metalsales,DC=us,DC=com)(primaryGroupID=512)))" ;

		// Set up the environment for creating the initial context
		Hashtable<String, String> env = new Hashtable<String, String>() ;
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory") ;
		env.put(Context.PROVIDER_URL, "ldap://192.168.100.18:389/") ;

		// Authenticate 'username' with 'password' to by attempting to bind to the LDAP server
		env.put(Context.SECURITY_AUTHENTICATION, "simple") ;
		env.put(Context.SECURITY_PRINCIPAL, "ldapservice@metalsales.us.com") ;
		env.put(Context.SECURITY_CREDENTIALS, "we1c0me") ;
		env.put(Context.REFERRAL, "follow") ;

		try {
			DirContext ctx = new InitialDirContext(env) ;
			SearchControls sctrls = new SearchControls() ;
			sctrls.setSearchScope(SearchControls.SUBTREE_SCOPE) ;
			sctrls.setDerefLinkFlag(true) ;
			NamingEnumeration<SearchResult> results ;
	 		results = ctx.search("dc=metalsales,dc=us,dc=com",filter,filterArgs,sctrls) ;

	 		if (results.hasMore()) {
	 			isAdmin = true ;
	 		}
	 		
	 		results.close() ;
	 		results = null ;
	 		ctx.close() ;
	 		ctx = null ;
	 		sctrls = null ;
	 		
		} catch (NamingException ne) {
			isAdmin = false ;
			System.out.println("LDAP Error") ;
			ne.printStackTrace() ;
		}

		return isAdmin ;
	}

	public static String getDisplayName(String username) {
		String displayName = null ;

		String[] filterArgs = {username} ;
		String filter = "(samaccountName={0})" ;

		// Set up the environment for creating the initial context
		Hashtable<String, String> env = new Hashtable<String, String>() ;
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory") ;
		env.put(Context.PROVIDER_URL, "ldap://192.168.100.18:389/") ;

		// Authenticate 'username' with 'password' to by attempting to bind to the LDAP server
		env.put(Context.SECURITY_AUTHENTICATION, "simple") ;
		env.put(Context.SECURITY_PRINCIPAL, "ldapservice@metalsales.us.com") ;
		env.put(Context.SECURITY_CREDENTIALS, "we1c0me") ;
		env.put(Context.REFERRAL, "follow") ;

		try {
			DirContext ctx = new InitialDirContext(env) ;
			SearchControls sctrls = new SearchControls() ;
			sctrls.setSearchScope(SearchControls.SUBTREE_SCOPE) ;
			sctrls.setDerefLinkFlag(true) ;
			NamingEnumeration<SearchResult> results ;
	 		results = ctx.search("dc=metalsales,dc=us,dc=com",filter,filterArgs,sctrls) ;

	 		if (results.hasMore()) {
	 			SearchResult current = results.next() ;
	 			Attributes attr = current.getAttributes() ;
	 			displayName = (String) attr.get("displayName").get(0) ;
	 			attr = null ;
	 			current = null ;
	 		}

	 		results.close() ;
	 		results = null ;
	 		ctx.close() ;
	 		ctx = null ;
	 		sctrls = null ;
	 		
		} catch (NamingException ne) {
			System.out.println("LDAP Error") ;
			ne.printStackTrace() ;
		}

		if (displayName==null) {
			displayName = "" ;
		}

		return displayName ;
	}

	public static String getMailAddress(String username) {
		String userMailAddr = new String() ;

		String[] filterArgs = {username} ;
		String filter = "(samaccountName={0})" ;

		// Set up the environment for creating the initial context
		Hashtable<String, String> env = new Hashtable<String, String>() ;
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory") ;
		env.put(Context.PROVIDER_URL, "ldap://192.168.100.18:389/") ;

		// Authenticate 'username' with 'password' to by attempting to bind to the LDAP server
		env.put(Context.SECURITY_AUTHENTICATION, "simple") ;
		env.put(Context.SECURITY_PRINCIPAL, "ldapservice@metalsales.us.com") ;
		env.put(Context.SECURITY_CREDENTIALS, "we1c0me") ;
		env.put(Context.REFERRAL, "follow") ;

		try {
			DirContext ctx = new InitialDirContext(env) ;
			SearchControls sctrls = new SearchControls() ;
			sctrls.setSearchScope(SearchControls.SUBTREE_SCOPE) ;
			sctrls.setDerefLinkFlag(true) ;
			NamingEnumeration<SearchResult> results ;
	 		results = ctx.search("dc=metalsales,dc=us,dc=com",filter,filterArgs,sctrls) ;

	 		if (results.hasMore()) {
	 			SearchResult current = results.next() ;
	 			Attributes attr = current.getAttributes() ;
	 			userMailAddr = (String) attr.get("mail").get(0) ;
	 			attr = null ;
	 			current = null ;
	 		}

	 		results.close() ;
	 		results = null ;
	 		ctx.close() ;
	 		ctx = null ;
	 		sctrls = null ;
	 		
		} catch (NamingException ne) {
			System.out.println("LDAP Error") ;
			ne.printStackTrace() ;
		}

		if (userMailAddr==null) {
			userMailAddr=username+"@metalsales.us.com" ;
		}

		return userMailAddr ;
	}
}