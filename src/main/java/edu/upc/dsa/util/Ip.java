package edu.upc.dsa.util;

import java.net.InetAddress;
import java.util.ResourceBundle;

public class Ip {
	public static String getIP(){
		try {
			String ip= InetAddress.getLocalHost().getHostAddress();
			System.out.println(ip);
			if (ip.equals("147.83.7.206")){
				return ip;
			}
			else{
				return "localhost";
			}
		}
		catch (Exception e){
			return "localhost";
		}
	}
}
