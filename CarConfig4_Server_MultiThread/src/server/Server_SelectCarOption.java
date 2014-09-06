package server;

import java.io.*;
import java.net.*;
import java.util.*;

import uitil.FileIO;
import Adapter.BuildAuto;
import Adapter.CreateAuto;
import model.Automobile;

public class Server_SelectCarOption extends DefaultSocketClient {
	// Socket sock;
	BuildAuto auto;

	public Server_SelectCarOption(Socket sock) {
		super(sock);
	}

	public boolean openConnection() {
		// need to push all the server local files into LHM
		return true;
	}

	public void loadServer() throws IOException, ClassNotFoundException {

//		FileInputStream fileIn = new FileInputStream("auto.ser");
//		ObjectInputStream objIn = new ObjectInputStream(fileIn);
//		Object obj1 = objIn.readObject();
//		Properties prop1 = (Properties) obj1;
		// prop1.load(in);

		auto = new BuildAuto();
		// push 3 cars into LHM garage
		
		auto.buildAuto("Prius.properties");
		// auto.putAutoFromPropertyObjectIntoGarage(prop1);
		auto.buildAuto("Civic.properties");
	

	}

	public void run() // override
	{
		if (openConnection()) {
			// here I load the server with a LHM that contains autos
			try {
				loadServer();

			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			handleSession();
		} // run
	}

	public void handleSession() {
		try {
			PrintWriter strOut = null;
			BufferedReader strIn = null;
			//ObjectOutputStream objWriter = null;
			strOut = new PrintWriter(this.getSock().getOutputStream(), true);
			strIn = new BufferedReader(new InputStreamReader(this.getSock()
					.getInputStream()));

			int carNum = auto.getGarage().size();
			Set<String> models = auto.getGarage().keySet();

			strOut.println("Hi, " + "choose one model you want "
					+ "to get from the following " + carNum + " cars:"
					+ models.toString());
			strOut.flush();
			ArrayList<String> choicePool = new ArrayList<String>();
			Automobile choiceCar = new Automobile();
			String state = new String();
			state="Pre_start";
			while (true) {
				String str = strIn.readLine();
				if (str == null) {
					break;
				} else if(state.equals("Pre_start")){
					state="start";
					
				}else if (state.equals("start")) {
					if (models.contains(str)) {
//						FileIO fi=new FileIO();
//						Properties props = fi.ReadProperties(str+".properties");
//					    
//						//send out props to client
//						ObjectOutputStream oos = null;
//						System.out.println(props.getProperty("Options1"));
//						oos.writeObject(props);
//						oos.flush();
//						//oos.close();
						
												
						state="pickColor";
						choiceCar = auto.getGarage().get(str);
						choicePool = choiceCar.getOptionNamesinSet("Color");
						strOut.println("Server:yes, I have that."
								+ " Please choose your color:"
								+ choicePool.toString());
						strOut.flush();
					}
					else if (str.trim().equalsIgnoreCase("BYE")){
						strOut.println("bye");
					    strOut.flush();
						break;
					}
					else{
						state="start";
						strOut.println("Sorry, I don't have that: "+str);
						strOut.flush();
					}
				} else if (state.equals("pickColor")){
					if ((choicePool.contains(str))){
					state="pickTransmission";
					choiceCar.setOptionChoice("Color", str);
					choicePool = choiceCar.getOptionNamesinSet("Transmission");
					strOut.println("OK, now please choose Transimission:"
							+ choicePool.toString());
					strOut.flush();
				    }
					else if (str.trim().equalsIgnoreCase("BYE")){
						strOut.println("bye");
					    strOut.flush();
						break;
					}
					else{
						state="pickColor";
						strOut.println("Sorry, I don't have that: "+str);
						strOut.flush();
					}
				}else if (state.equals("pickTransmission")){
					if ((choicePool.contains(str))){
					state="pickbreak";
					choiceCar.setOptionChoice("Transmission", str);
					choicePool = choiceCar.getOptionNamesinSet("Break");
					strOut.println("OK, now please choose break:"
							+ choicePool.toString());
					strOut.flush();
				    }
					else if (str.trim().equalsIgnoreCase("BYE")){
						strOut.println("bye");
					    strOut.flush();
						break;
					}
					else{
						state="pickTransmission";
						strOut.println("Sorry, I don't have that: "+str);
						strOut.flush();
					}
				}else if (state.equals("pickbreak")){
					if ((choicePool.contains(str))){
					state="pickBreak/TractionControl";
					choiceCar.setOptionChoice("Break", str);
					choicePool = choiceCar.getOptionNamesinSet("Break/TractionControl");
					strOut.println("OK, now please choose TractionControl:"
							+ choicePool.toString());
					strOut.flush();
				    }
					else if (str.trim().equalsIgnoreCase("BYE")){
						strOut.println("bye");
					    strOut.flush();
						break;
					}
					else{
						state="pickbreak";
						strOut.println("Sorry, I don't have that: "+str);
						strOut.flush();
					}
					
				}else if (state.equals("pickBreak/TractionControl")){
						if ((choicePool.contains(str))){
						state="completed";
						choiceCar.setOptionChoice("Break/TractionControl", str);
						strOut.println("completed");
						strOut.flush();
					    }
						else if (str.trim().equalsIgnoreCase("BYE")){
							strOut.println("bye");
						    strOut.flush();
							break;
						}
						else{
							state="pickBreak/TractionControl";
							strOut.println("Sorry, I don't have that: "+str);
							strOut.flush();
						}
				}
				else {
					strOut.println("You've done your choice.");
					strOut.flush();
					//break;
				}
			}
			strOut.close();
			strIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = null; // new as serverSocket using port 5556
		boolean listening = true;
		try {
			serverSocket = new ServerSocket(9999);
			System.out.println("Server: I am serving ");
		} catch (IOException e) {
			System.err.println("Could not listen on port:9999.");
			System.exit(1);
		}
		while (listening) {
			Server_SelectCarOption myserver = new Server_SelectCarOption(
					serverSocket.accept());
			myserver.start();

		}
		serverSocket.close();
	}
}


