package WebScrap;

import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.net.URL ;
import java.util.Scanner ;


public class WebScrapTest
{
	//http://www.google.com

	public static void main(String[] args)
	{
		try
		{
//			URL url = new URL("https://www.wikipedia.com/") ;
			URL url = new URL("http://canyouseeme.org/") ;
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())) ;
			
//			String string = "" ;
//			while ((string = reader.readLine()) != null)
//			{
//				if (string.contains("href=\"http://"))
//				System.out.println(string) ;
//			}
			
			Scanner scan = new Scanner(url.openStream()) ;
			
			
			String keyword = "<input id=\"ip\" type=\"text\" value=\"" ;
			while(scan.hasNext())
			{
				String next = scan.nextLine().trim() ;
				
				if (next.contains(keyword))//" href=\"http://"   
				{
					
					String ip = next.substring(keyword.length()) ;
					ip = ip.substring(0, ip.indexOf("\"")) ;
					System.out.println(ip) ;
				}
			}
			reader.close() ;
			
		}
		catch (Exception e)
		{
			System.out.println("ERROR: " + e.getMessage()) ;
		}
		
	}
}
/**
 * Tutoriales Java 29 - Leer una web con Java
 * https://www.youtube.com/watch?v=oiegaju7qIk
 * 
 * Qué es y cómo hacer «web scraping» en Java
 * https://picodotdev.github.io/blog-bitix/2014/10/que-es-y-como-hacer-web-scraping-en-java/
 * 
 * How to Read HTML from a URL in Java
 * https://www.youtube.com/watch?v=JcZwlpV1rMc
 * 
 * Java reading element from web page
 * https://www.youtube.com/watch?v=5NCe40LKCdU 
 * 
 * Reading Web Pages and Scraping Links (Java)
 * https://www.youtube.com/watch?v=1aJGi2bPHas
 * 
 * 
 * 
 * 
 */