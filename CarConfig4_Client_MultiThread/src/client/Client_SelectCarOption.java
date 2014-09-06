package client;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import model.Automobile;
import Adapter.BuildAuto;
import uitil.FileIO;

public class Client_SelectCarOption extends Step4_DefaultSocketClient {

	public Client_SelectCarOption(String strHost, int iPort) {
		super(strHost, iPort);
	}

	public void handleSession() throws IOException, ClassNotFoundException {

		BufferedReader stdIn = new BufferedReader(new InputStreamReader(
				System.in));
		String fromServer;
		//ObjectInputStream objectReader;
		PrintWriter out = new PrintWriter(this.getSock().getOutputStream(), true);
		BufferedReader in = new BufferedReader(new InputStreamReader(this.getSock().getInputStream()));
       
        
		String userInput;
		Object obj;
        System.out.println("Press any key to start:");
		
        
        //ObjectInputStream ois = new ObjectInputStream(this.getSock().getInputStream());
        //obj=ois.readObject();
        
        /*
         * if (obj!=null){
            	Properties prop1 = (Properties) obj;
            	//System.out.println(auto.getMake());
            	
            	 BuildAuto auto = new BuildAuto();
				 auto.putAutoFromPropertyObjectIntoGarage(prop1);
				 System.out.println(auto.getNewInputAuto().getMake());
				 System.out.println("Server:yes, I have that."
								+ " Please choose your color:");
            }
        	else
         */
        
        
        
        while ((  (userInput=stdIn.readLine())!=null)   ) {
             if (userInput.equals("bye"))
				{System.out.println("OK,Bye!");
                break;
                }
			else{out.println(userInput);
			System.out.println(in.readLine());
			}
			}
		out.close();
		in.close();
		this.getSock().close();
	}
		
	
	public static void main(String arg[]) throws IOException{
		/* debug main; does daytime on local host */
		String strLocalHost = "127.0.0.1";
		Client_SelectCarOption client = new Client_SelectCarOption(
				strLocalHost, 9999);
		client.start();
	}
	
}

			


