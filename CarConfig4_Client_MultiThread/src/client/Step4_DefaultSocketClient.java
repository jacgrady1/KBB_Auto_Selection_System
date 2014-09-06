package client;

import java.io.*;
import java.net.*;
import java.util.Properties;

import model.Automobile;

public class Step4_DefaultSocketClient extends Thread implements SocketClientInterface,SocketClientConstants{
	
	private BufferedReader reader;
	private BufferedWriter writer;
	private Socket sock;
	private String strHost; // host name
	private int iPort; // port num 
	private ObjectInputStream objectReader;
	private ObjectOutputStream objectWriter;
	
	
	public BufferedReader getReader() {
		return reader;
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public String getStrHost() {
		return strHost;
	}

	public int getiPort() {
		return iPort;
	}

	public ObjectInputStream getObjectReader() {
		return objectReader;
	}

	public ObjectOutputStream getObjectWriter() {
		return objectWriter;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}

	public void setObjectReader(ObjectInputStream objectReader) {
		this.objectReader = objectReader;
	}

	public void setObjectWriter(ObjectOutputStream objectWriter) {
		this.objectWriter = objectWriter;
	}

	public Socket getSock() {
		return sock;
	}

	public void setSock(Socket sock) {
		this.sock = sock;
	}

	public Step4_DefaultSocketClient(String strHost, int iPort)
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
			try {
				handleSession();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));
		}
		catch (Exception e){
			if (DEBUG) System.err.println("Unable to obtain stream to/from"+strHost);
			return false;
		}
		return true;
	}
	
	public void handleSession() throws IOException,ClassNotFoundException { 
		// handleSession is where we read data and pass it to a helper methods handleInput
		String strInput = "";
		if (DEBUG) System.out.println("Handling session with "+strHost + ":"+iPort);
			while ((strInput = reader.readLine())!=null)
				handleInput(strInput);
	}
	
	
	public void sendOutput(Properties props) throws IOException
	{
		try
		{
			objectWriter.writeObject(props);
			objectWriter.flush();
		}
		catch(Exception e)
		{
			e.printStackTrace();
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
