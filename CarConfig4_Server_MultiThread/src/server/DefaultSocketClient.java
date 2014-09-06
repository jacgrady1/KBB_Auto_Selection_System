package server;

import java.io.*;
import java.net.*;

public class DefaultSocketClient extends Thread implements SocketClientInterface,SocketClientConstants{
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket sock;
	private String strHost; // host name
	private int iPort; // port num 
	
	
	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public DefaultSocketClient(Socket sock){
		this.sock=sock;
	}
	public DefaultSocketClient(String strHost, int iPort)
	{
		setPort (iPort);
		setHost(strHost);
	}//constructor
	
	public void setHost(String strHost){
		this.strHost=strHost;
	}
	public void setPort(int iPort){
		this.iPort=iPort;
	}
	
	public void run() // override
	{
		if (openConnection()){
			handleSession();
			closeSession();
		} //run 
		
	}
	
	public boolean openConnection()
	{
		try
		{
			sock = new Socket(strHost,iPort);
		}
		catch(IOException socket){
			if (DEBUG) System.err.println("Unable to connect to "+strHost);
			return false;
		}
		try {
			reader = new BufferedReader (new InputStreamReader (sock.getInputStream()));
			// might need to change input type here in unit 4 --- Object Strings so that is it...
			writer = new BufferedWriter(new OutputStreamWriter (sock.getOutputStream()));
		}
		catch (Exception e){
			if (DEBUG) System.err.println("Unable to obtain stream to/from"+strHost);
			return false;
		}
		return true;
	}
	
	public void handleSession() { // what server should do to handle the session ? 
		// handleSession is where we read data and pass it to a helper methods handleInput
		String strInput = "";
		if (DEBUG) System.out.println("Handling session with "+strHost + ":"+iPort);
		// reader contains a proper file need to 
		try {
			while ((strInput = reader.readLine())!=null)
				handleInput(strInput);
		}
		catch(IOException e){
			if (DEBUG) System.out.println("Handling session with " + strHost + ":"+iPort);
		}
	}
	
	public void sendOutput(String strOutput){
		// send response back to the server 
		// reading from a server , write to a server ... Cool 
		try{
			writer.write(strOutput,0,strOutput.length());
		}
		catch(IOException e){
			if (DEBUG) System.out.println("Error writing to "+ strHost);
			
		}
	}

	public void handleInput(String strInput){
		// overide this to do what we need to do 
		System.out.println(strInput);
	}
	public void closeSession(){
		try{
			writer = null;
			reader = null;
			sock.close();
		}
		catch(IOException e){
			if (DEBUG) System.err.println("Error closing socket to "+ strHost);
		}
	}

	

	
}

