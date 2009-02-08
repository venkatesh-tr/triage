package com.blogspot.devenphillips.helpdesk.generators.imap ;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.HashMap;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

public class IMAPGenerator {
	// TODO Write IMAP Generator. Should start a new thread to monitor the IMAP mailbox

	public IMAPGenerator() {
		// Create a new thread and attach the thread object to the WebApp object for 
		// notification purposes
		
	}

	/**
	 * Display an Hbox object which contains all of the inputs needed to configure the 
	 * IMAP module
	 * @return An Hbox object which has components and events to configure the IMAP module
	 */
	public static Hbox showConfig() {
		java.io.InputStream zulInput = IMAPGenerator.class.getClassLoader().getResourceAsStream("com/blogspot/devenphillips/helpdesk/generators/imap/imapconfig.zul") ;
		Reader zulReader = new InputStreamReader(zulInput) ;
		Hbox retVal = new Hbox() ;
		try {
			retVal = (Hbox)Executions.createComponentsDirectly(zulReader,"zul",null,new HashMap<String,String>()) ;
		} catch (Exception e) {
			Window errWin = new Window() ;
			errWin.setTitle("Configuration Error") ;
			try {
				errWin.setMode("overlapped") ;
			} catch (InterruptedException e1) {
				// This catch block will never be reached because the window is not modal.
			}
			Label errMessage = new Label("Unable to load configuration interface for IMAP.") ;
			errWin.setWidth("400px") ;
			errWin.setHeight("200px") ;
			errWin.appendChild(errMessage) ;
			retVal.appendChild(errWin) ;
		}
		return retVal ;
	}

	/**
	 * Get the version identifier for this module. The version identifier may later be used to check
	 * for newer versions of modules and allow for automatic updates to the application.
	 * @return The current version identifier in <major>.<minor>.<micro> format.
	 */
	public static String getVersion() {
		return "0.0.1" ;
	}

	/**
	 * Get the short name of the module
	 * @return A short name which can be displayed easily in the user interface
	 */
	public static String getModuleShortName() {
		return "IMAP" ;
	}

	/**
	 * Get the full name of the module
	 * @return Returns a langer name which gives a bit more detail about the module.
	 */
	public static String getModuleName() {
		return "IMAP Ticket Generator" ;
	}

	/**
	 * Get the full description of the module
	 * @return A String containing a detailed description of the module and it's functions.
	 */
	public static String getModuleDescription() {
		return "This module allows management of tickets via an IMAP account. New messages coming in generate a new ticket with the message body as the ticket body. The submitting user is determined by the source e-mail address. After the ticket is created, a response is sent to the submitter with the subject and a ticket identifier. Subsequent e-mails which contain the matching ticket identifier are appended as notes to the same ticket." ;
	}
}
