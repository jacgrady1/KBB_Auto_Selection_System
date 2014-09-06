package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import model.Automobile;
import uitil.FileIO;
import Adapter.BuildAuto;
import Adapter.CreateAuto;

public class CarModelOptionsIO extends DefaultSocketClient{
       
	    
		public CarModelOptionsIO(String strHost, int iPort) {
			super(strHost, iPort);
	}

		public void handleSession(){
		    boolean Err=false;
			while (!Err){
				try{
				System.out.println("Client: Enter the name of file you want to send: ");
				System.out.println(" (hint:enter test1.properties or test2.properties)");
			    
				BufferedReader userIn = new BufferedReader(new InputStreamReader(System.in));
				
				String filename = userIn.readLine();
				FileIO output =new FileIO();
				Properties props = output.ReadProperties(filename);
				
				sendOutput(props);
				
				// sent out props to server 
				// now receive buffer from server to verify
				BufferedReader in = null;
				in = new BufferedReader(new InputStreamReader(this.getSock().getInputStream()));
				String fromServer= in.readLine();
				System.out.println("fromServer:"+fromServer);
				}
				
				catch(IOException e){
					if (DEBUG) System.out.println("Handling session with client");
				}

			}
		}
		
		
		
		public static void main(String arg[]){
			String strLocalHost="127.0.0.1";
      		CarModelOptionsIO client = new CarModelOptionsIO (strLocalHost, 9999);
			client.start();
		}

		
		
		
	}

