package com.blogspot.devenphillips.helpdesk.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class Bootstrap extends GenericServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1318678239566487507L;

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

	public void init() {
		Properties config = new Properties() ;
		boolean isConfigured = true ;
		try {
			config.load(new FileInputStream("helpdesk.properties")) ;
		} catch (FileNotFoundException e) {
			this.getServletContext().log("Error loading properties file. Application is not yet configured.",e) ;
			isConfigured = false ;
		} catch (IOException e) {
			this.getServletContext().log("Error loading properties file. Application is not yet configured.",e) ;
			isConfigured = false ;
		}

		if (isConfigured) {
/*			String dbhost = config.getProperty("db.host") ;
			String dbname = config.getProperty("db.name") ;
			String dbdialect = config.getProperty("db.dialect") ;
			String dbuser = config.getProperty("db.user") ;
			String dbpass = config.getProperty("db.pass") ;*/
			this.getServletContext().setAttribute("isConfigured", "true") ;
		} else {
			this.getServletContext().setAttribute("isConfigured", "false") ;
		}
	}
}
