
import java.io.BufferedReader ;
import java.io.IOException ;
import java.io.InputStream ;
import java.io.InputStreamReader ;
import java.net.InetAddress ;
import java.net.NetworkInterface ;
import java.net.ServerSocket ;
import java.net.Socket ;
import java.net.SocketException ;
import java.net.URL ;
import java.net.URLConnection ;
import java.net.UnknownHostException ;
import java.util.Collections ;
import java.util.Enumeration ;
import java.util.Formatter ;
import java.util.Scanner ;

public class JavaTest
{
	public static void main0(String[] args)
	{
		
		try
		{
			URL url = new URL("https://docs.google.com/document/d/187EuGiFEiRJTTfdcP7jmEmtR2phJD965tSok4ZBgL2M/edit?usp=sharing") ;
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream())) ;
			
			String string = "" ;
			while ((string = reader.readLine()) != null)
			{
//				if (string.contains("href=\"http://"))
				System.out.println(string) ;
			}
			
			reader.close() ;
			
		}
		catch (Exception e)
		{
			System.out.println("ERROR: " + e.getMessage()) ;
		}
	}
	
	
	public static void main1(String[] args)
	{
		try
		{
			InetAddress ip = InetAddress.getLocalHost() ;
			
			System.out.println(ip.getHostAddress()) ;
		}
		catch (UnknownHostException e)
		{
			System.out.println("ERROR: " + e.getMessage()) ;
		}
		
			System.out.println("1: " + whatismyip("http://whatsmyip.net")) ;
			System.out.println("2: " + whatismyip("http://checkip.amazonaws.com/")) ;
			System.out.println("3: " + whatismyip("http://ipecho.net/plain")) ;
			System.out.println("4: " + whatismyip("http://icanhazip.com/")) ;
			System.out.println("5: " + whatismyip("http://www.trackip.net/ip")) ;
			System.out.println("6: " + whatismyip("http://myexternalip.com/raw")) ;
			System.out.println("7: " + whatismyip("http://whatismyip.org")) ;
		
//*****************************************************************************************
		//65535
//		ServerSocket s = null  ;
//		for (int i = 1; i <= 65535; i++)
//		{
//			try
//			{
//
//				s = new ServerSocket(i) ;
//				//System.out.println(s.getLocalPort() + "  ==>  " + s.isBound()) ;
//				// System.out.println(s.getLocalPort()) ;
//			}
//			catch (IOException e)
//			{
//				System.out.println(s.getLocalPort() + "  ==>  " + e.getMessage()) ;
//			}
//		}
		
//*****************************************************************************************

		
		
/*		ServerSocket s = null ;
		try
		{
			s = new ServerSocket(0) ;
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("listening on port: " + s.getLocalPort());
		try
		{
			s.close();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
	}

	/**http://whatsmyip.net
	 * http://checkip.amazonaws.com/ 
	 * http://ipecho.net/plain
	 * http://icanhazip.com/ 
	 * http://www.trackip.net/ip
	 * http://myexternalip.com/raw 
	 * http://whatismyip.org/s
	 */
	private static String whatismyip(String url)
	{
		try
		{
			URL connection = new URL(url) ;
			URLConnection con = connection.openConnection() ;
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream())) ;
			return reader.readLine() ;
		}
		catch (Exception e)
		{
			return "ERROR ==> Exception : " + e.getMessage()  + "\n===>> " +url ;
		}
	}/*
	
	public String getIpAddress() {
		   try {
		         for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
		                NetworkInterface intf = en.nextElement();
		             for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
		                    InetAddress inetAddress = enumIpAddr.nextElement();
		                    if (!inetAddress.isLoopbackAddress()) {
		                        String ip = Formatter.formatIpAddress(inetAddress.hashCode());
		                        Log.d("VPNConnected",ip);
		                        return ip;
		                    }
		                }
		            }

		        } catch (Exception ex) {
		            Log.d("exception", ex.toString());
		        }
		        return "EMPTY";
		    }*/
	
	public final static String WIP_SITE = "http://whatsmyip.net" ;
	private final static String REPLACE_PATTERN = "(?s).*?(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}).*" ;

	// For demo
	public static void main3(String[] args)
	{
		System.out.println(whatIsMyIp()) ;
	}

	public static String whatIsMyIp()
	{
		String result = null ;
		InputStream in = null ;

		try
		{
			URLConnection conn = new URL(WIP_SITE).openConnection() ;
			int length = Integer.valueOf(conn.getHeaderField("Content-Length")) ;
			byte[] buf = new byte[length] ;
			in = conn.getInputStream() ;
			in.read(buf) ;
			result = new String(buf) ;
			result = result.replaceAll(REPLACE_PATTERN, "$1") ;
		}
		catch (IOException e)
		{
			e.printStackTrace() ;
		}
		finally
		{
			if (in != null)
			{
				try
				{
					in.close() ;
				}
				catch (IOException e)
				{ /* ignore */
				}
			}
		}

		return result ;
	}
	
	public static void main(String[] args)
	{
		
	}
	
}













































