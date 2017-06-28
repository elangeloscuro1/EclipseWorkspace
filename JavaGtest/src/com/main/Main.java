package com.main ;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin ;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow ;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential ;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport ;
import com.google.api.client.http.HttpResponseException ;
import com.google.api.client.http.HttpTransport ;
import com.google.api.client.http.javanet.NetHttpTransport ;
import com.google.api.client.json.jackson.JacksonFactory ;
import com.google.gdata.client.authn.oauth.* ;
import com.google.gdata.client.docs.DocsService ;
import com.google.gdata.client.spreadsheet.* ;
import com.google.gdata.data.* ;
import com.google.gdata.data.batch.* ;
import com.google.gdata.data.docs.DocumentListEntry ;
import com.google.gdata.data.docs.DocumentListFeed ;
import com.google.gdata.data.spreadsheet.* ;
import com.google.gdata.util.* ;

import java.io.File ;
import java.io.IOException ;
import java.net.* ;
import java.security.GeneralSecurityException ;
import java.util.* ;

public class Main
{


	 public static void main(String[] args) throws MalformedURLException, GeneralSecurityException, IOException, ServiceException {
	        URL SPREADSHEET_FEED_URL  = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");

	        File p12 = new File("PZBu9OfmQxfLbaHsPcOJJr-f");

	        HttpTransport httpTransport = new NetHttpTransport();
	        JacksonFactory jsonFactory = new JacksonFactory();
	        String[] SCOPESArray = 
	        	{
	        		"https://spreadsheets.google.com/feeds", 
	        		"https://spreadsheets.google.com/feeds/spreadsheets/private/full", 
	        		"https://docs.google.com/feeds"
	        	};
	        final List SCOPES = Arrays.asList(SCOPESArray);
	        
	        GoogleCredential credential = new GoogleCredential.Builder()
	                .setTransport(httpTransport)
	                .setJsonFactory(jsonFactory)
	                .setServiceAccountId("1033508477044-lnrvt09gcuh94icqaqbd5lq65t9gd2ga.apps.googleusercontent.com")
	                .setServiceAccountScopes(SCOPES)
	                .setServiceAccountPrivateKeyFromP12File(p12)
	                .build();

	        SpreadsheetService service = new SpreadsheetService("Test");

	        service.setOAuth2Credentials(credential);
	        
	        SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
	        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

	        if (spreadsheets.size() == 0) {
	            System.out.println("No spreadsheets found.");
	        }

	         SpreadsheetEntry spreadsheet = null;
	        for (int i = 0; i < spreadsheets.size(); i++) {
	            if (spreadsheets.get(i).getTitle().getPlainText().startsWith("ListOfSandboxes")) {
	                spreadsheet = spreadsheets.get(i);
	                System.out.println("Name of editing spreadsheet: " + spreadsheets.get(i).getTitle().getPlainText());
	                System.out.println("ID of SpreadSheet: " + i);
	            }
	        }

	    }

	public static void main05(String[] args) throws IOException
	{
		// HttpTransport used to send login request.
		HttpTransport transport = new NetHttpTransport() ;
		try
		{
			// authenticate with ClientLogin
			ClientLogin authenticator = new ClientLogin() ;
			authenticator.transport = transport ;
			// Google service trying to access, e.g., "cl" for calendar.
			authenticator.authTokenType = "cl" ;
			authenticator.username = "elangeloscuro1" ;
			authenticator.password = "Anta4759" ;
			authenticator.authenticate() ;
			System.out.println("Authentication succeeded.") ;
		}
		catch (HttpResponseException e)
		{
			// Likely a "403 Forbidden" error.
			System.err.println(e.getStatusMessage()) ;
			throw e ;
		}
	}
	public static void main04(String[] args) throws MalformedURLException, GeneralSecurityException, IOException, ServiceException {
        URL SPREADSHEET_FEED_URL;
        SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full");
/*
        File p12 = new File("./key.p12");

        HttpTransport httpTransport = new NetHttpTransport();
        JacksonFactory jsonFactory = new JacksonFactory();
        String[] SCOPESArray = {"https://spreadsheets.google.com/feeds", "https://spreadsheets.google.com/feeds/spreadsheets/private/full", "https://docs.google.com/feeds"};
        final List SCOPES = Arrays.asList(SCOPESArray);
        GoogleCredential credential = new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId("cliend_ID")
                .setServiceAccountScopes(SCOPES)
                .setServiceAccountPrivateKeyFromP12File(p12)
                .build();

        SpreadsheetService service = new SpreadsheetService("Test");

        service.setOAuth2Credentials(credential);
        
        SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class);
        List<SpreadsheetEntry> spreadsheets = feed.getEntries();

        if (spreadsheets.size() == 0) {
            System.out.println("No spreadsheets found.");
        }

         SpreadsheetEntry spreadsheet = null;
        for (int i = 0; i < spreadsheets.size(); i++) {
            if (spreadsheets.get(i).getTitle().getPlainText().startsWith("ListOfSandboxes")) {
                spreadsheet = spreadsheets.get(i);
                System.out.println("Name of editing spreadsheet: " + spreadsheets.get(i).getTitle().getPlainText());
                System.out.println("ID of SpreadSheet: " + i);
            }
        }
	*/
    }

	
	
	public static void main03(String[] args)
			throws AuthenticationException, MalformedURLException, IOException, ServiceException
	{

		SpreadsheetService service = new SpreadsheetService("MySpreadsheetIntegration-v1") ;

		// TODO: Authorize the service object for a specific user (see other
		// sections)
		String USERNAME = "elangeloscuro1@gmail.com" ;
		String PASSWORD = "Anta4759" ;

		service.setUserCredentials(USERNAME, PASSWORD) ;

		// TODO: Authorize the service object for a specific user (see other
		// sections)

		// Define the URL to request. This should never change.
		URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full") ;

		// Make a request to the API and get all spreadsheets.
		SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class) ;
		List<SpreadsheetEntry> spreadsheets = feed.getEntries() ;

		if (spreadsheets.size() == 0)
		{
			// TODO: There were no spreadsheets, act accordingly.
		}

		// TODO: Choose a spreadsheet more intelligently based on your
		// app's needs.
		SpreadsheetEntry spreadsheet = spreadsheets.get(0) ;
		System.out.println(spreadsheet.getTitle().getPlainText()) ;

		// Get the first worksheet of the first spreadsheet.
		// TODO: Choose a worksheet more intelligently based on your
		// app's needs.
		WorksheetFeed worksheetFeed = service.getFeed(spreadsheet.getWorksheetFeedUrl(), WorksheetFeed.class) ;
		List<WorksheetEntry> worksheets = worksheetFeed.getEntries() ;
		WorksheetEntry worksheet = worksheets.get(0) ;

		// Delete the worksheet via the API.
		worksheet.delete() ;
	}
	

	public static void main02(String[] args)
	{

		try
		{
			SpreadsheetService service = new SpreadsheetService("MySpreadsheetIntegration-v1") ;

			// TODO: Authorize the service object for a specific user (see other
			// sections)
			service.setUserCredentials("elangeloscuro1@gmail.com", "Anta4759") ;

			// Define the URL to request. This should never change.
			URL SPREADSHEET_FEED_URL = new URL("https://spreadsheets.google.com/feeds/spreadsheets/private/full") ;

			// Make a request to the API and get all spreadsheets.
			SpreadsheetFeed feed = service.getFeed(SPREADSHEET_FEED_URL, SpreadsheetFeed.class) ;
			List<SpreadsheetEntry> spreadsheets = feed.getEntries() ;

			// Iterate through all of the spreadsheets returned
			for (SpreadsheetEntry spreadsheet : spreadsheets)
			{
				// Print the title of this spreadsheet to the screen
				System.out.println(spreadsheet.getTitle().getPlainText()) ;
			}
		}
		catch (Exception e)
		{
			System.out.println("ERROR: " + e.getMessage()) ;
		}

	}

	public static void main01(String[] args) throws IOException, ServiceException
	{

//		 try
//		 {
			DocsService service = new DocsService("Documment List Demo") ;
			service.setUserCredentials("elangeloscuro1", "Anta4759") ;

			URL documentListFeedUrl = new URL("http://docs.google.com/feeds/documents/private/full") ;
			DocumentListFeed feed = service.getFeed(documentListFeedUrl, DocumentListFeed.class) ;

			for (DocumentListEntry entry : feed.getEntries())
			{
				System.out.println(entry.getTitle().getPlainText()) ;
			}
//		}
//		catch (Exception e)
//		{
//			System.out.println("ERROR: " + e.getMessage()) ;
//		}
		

	}
}
