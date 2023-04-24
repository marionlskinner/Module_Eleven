package cen3024;

import java.io.*;
import java.net.*;



public class Module11_Server extends Module11_Client {

	public static String primeNumber(int num) {
		
		if(num <2) {
			return "NO";
		}
		
		int i=2;
		
		while(i<num) {
			if(num%i==0) {
				return "NO";
			}
			
			i++;
		}
		
		return "YES";
	}
	
	public static void main(String[] args) {
		
		try {
			ServerSocket ss = new ServerSocket(8000);
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			int num = (int)dis.readInt();
			dout.writeUTF(primeNumber(num));
			dout.flush();
			
			dout.close();
			ss.close();
		}
			catch(Exception e) {
				System.out.println(e);
			}
	}
	
	
	
}