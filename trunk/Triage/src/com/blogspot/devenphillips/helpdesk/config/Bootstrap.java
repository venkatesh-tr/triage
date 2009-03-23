package com.blogspot.devenphillips.helpdesk.config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;

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
		Logger log = org.apache.log4j.LogManager.getLogger("com.blogspot.devenphillips.helpdesk") ;
		Properties config = new Properties() ;
		boolean isConfigured = true ;
		try {
			log.debug("Attempting to load configuration file if it exists.") ;
			config.load(new FileInputStream("helpdesk.properties")) ;
		} catch (FileNotFoundException e) {
			log.debug("Application configuration file does not exist.") ;
			this.getServletContext().log("Error loading properties file. Application is not yet configured.") ;
			isConfigured = false ;
		} catch (IOException e) {
			log.debug("Unable to read application configuration file.") ;
			this.getServletContext().log("Error loading properties file. Application is not yet configured.") ;
			isConfigured = false ;
		}

		if (isConfigured) {
/*			String dbhost = config.getProperty("db.host") ;
			String dbname = config.getProperty("db.name") ;
			String dbdialect = config.getProperty("db.dialect") ;
			String dbuser = config.getProperty("db.user") ;
			String dbpass = config.getProperty("db.pass") ;*/
			log.debug("Setting application context variable to indicate that the application is configured.") ;
			this.getServletContext().setAttribute("isConfigured", "true") ;
		} else {
			log.debug("Setting application context variable to indicate that the application is NOT configured.") ;
			this.getServletContext().setAttribute("isConfigured", "false") ;
		}
	}
}
