package server;

import java.io.*;
import java.net.*;
import java.util.*;

import uitil.FileIO;
import Adapter.BuildAuto;
import model.Automobile;

public class KBBServer extends DefaultSocketClient {
    //Socket sock;
	public KBBServer(Socket sock) {
		super(sock);
	}
	public boolean openConnection()
	{
		return true;
	}
	public void handleSession() { // what server should do to handle the session ? 
		// handleSession is where we read data and pass it to a helper methods handleInput
		if (DEBUG) System.out.println("Server Handling session");
		// reader contains a proper file  
		ObjectInputStream reader =null;
		while(true)
		{
		try {
			reader = new ObjectInputStream(this.getSock().getInputStream());
			// reader is an object
			Object obj;
			obj = reader.readObject();
			while ((obj)!= null){
				
                FileIO fi= new FileIO();
                Properties props = (Properties) obj;
                fi.SerializeAuto(props);
				
				 // here I need to push auto into LHM
				 FileInputStream fileIn = new FileInputStream("auto.ser");
		         ObjectInputStream in = new ObjectInputStream(fileIn);
		         Object obj1= in.readObject(); 
				 Properties prop1 = (Properties) obj1;
				 AutoServer auto = new BuildAuto();
				 auto.putAutoFromPropertyObjectIntoGarage(prop1); 
				 // here I send message to client 
			     PrintWriter writer = new PrintWriter(this.getSock().getOutputStream(), true);
                 writer.println("server received client");
                 
				
			}
		}
		catch(IOException e){
			if (DEBUG) System.out.println("IOException here");
		}
		catch(ClassNotFoundException e){
		    if (DEBUG) System.out.println("ClassNotFound");
		}
		}
	}

	
	public static void main(String[] args) throws IOException {

	        ServerSocket serverSocket = null; // new as serverSocket using port 5556
	        try {
	            serverSocket = new ServerSocket(9999); 
	            System.out.println("she serves");
	        } catch (IOException e) {
	            System.err.println("Could not listen on port: 9999.");
	            System.exit(1);
	        }

	        try {
	        	//System.out.println("heard from client!");
	            KBBServer myserver = new KBBServer(serverSocket.accept());   // hear client responding 
		        myserver.start();
	            
	        } catch (IOException e) {
	            System.err.println("Accept failed.");
	            System.exit(1);
	        }
    
	}
	}      
	        
	        
