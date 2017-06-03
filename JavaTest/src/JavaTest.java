
import java.io.BufferedReader ;
import java.io.InputStreamReader ;
import java.net.InetAddress ;
import java.net.NetworkInterface ;
import java.net.SocketException ;
import java.net.URL ;
import java.net.URLConnection ;
import java.net.UnknownHostException ;
import java.util.Collections ;
import java.util.Enumeration ;
import java.util.Formatter ;

public class JavaTest
{
	public static void main(String[] args)
	{
//		try
//		{
//			InetAddress ip = InetAddress.getLocalHost() ;
//			
//			System.out.println(ip.getHostAddress()) ;
//		}
//		catch (UnknownHostException e)
//		{
//			System.out.println("ERROR: " + e.getMessage()) ;
//		}
		
			System.out.println(whatismyip("http://checkip.amazonaws.com/")) ;
			System.out.println(whatismyip("http://ipecho.net/plain")) ;
			System.out.println(whatismyip("http://icanhazip.com/")) ;
			System.out.println(whatismyip("http://www.trackip.net/ip")) ;
			System.out.println(whatismyip("http://myexternalip.com/raw")) ;
			System.out.println(whatismyip("http://whatismyip.org")) ;
		

		
	}

	/**
	 * http://checkip.amazonaws.com/ 
	 * http://ipecho.net/plain
	 * http://icanhazip.com/ 
	 * http://www.trackip.net/ip
	 * http://myexternalip.com/raw 
	 * http://whatismyip.org/
	 * 
	 * @param url
	 * @return
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
	
}