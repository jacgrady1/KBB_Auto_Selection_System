package client;

import java.io.*;
import java.net.*;
import java.util.Properties;

import model.Automobile;

public class DefaultSocketClient extends Thread implements SocketClientInterface,SocketClientConstants{
	
	private BufferedReader reader;
	private Socket sock;
	private String strHost; // host name
	private int iPort; // port num 
	private ObjectOutputStream writer;
	
	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
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
			//closeSession();
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
			writer = new ObjectOutputStream(sock.getOutputStream());
		}
		catch (Exception e){
			if (DEBUG) System.err.println("Unable to obtain stream to/from"+strHost);
			return false;
		}
		return true;
	}
	
	public void handleSession(){ 
		// handleSession is where we read data and pass it to a helper methods handleInput
		String strInput = "";
		if (DEBUG) System.out.println("Handling session with "+strHost + ":"+iPort);
		try {
			while ((strInput = reader.readLine())!=null)
				handleInput(strInput);
		}
		catch(IOException e){
			if (DEBUG) System.out.println("Handling session with " + strHost + ":"+iPort);
		}
	}
	
	
	public void sendOutput(Properties props) throws IOException
	{
		try
		{
			writer.writeObject(props);
			writer.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

//	public void sendOutput(Automobile auto) throws IOException
//	{
//		//System.out.println("DefaultSockettest"+props.size());
//		
//		try
//		{
//			writer.writeObject(auto);
//			writer.flush();
//		}
//		catch(Exception e)
//		{
//			e.printStackTrace();
//		}
//
//	}

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

	
//	public static void main(String arg[]){
//	/* debug main; does daytime on local host */
//	String strLocalHost="";
//	try {
//		strLocalHost=InetAddress.getLocalHost().getHostName();
//	}
//	catch (UnknownHostException e){
//		System.err.println("Unable to find local host");
//	}
//	DefaultSocketClient d = new DefaultSocketClient (strLocal Host, iDAYTIME_PORT);
//	d.start();

	
}

//public static void main(String arg[]){
//	/* debug main; does daytime on local host */
//	String strLocalHost="";
//	try {
//		strLocalHost=InetAddress.getLocalHost().getHostName();
//	}
//	catch (UnknownHostException e){
//		System.err.println("Unable to find local host");
//	}
//	DefaultSocketClient d = new DefaultSocketClient (strLocal Host, iDAYTIME_PORT);
//	d.start();
//}
