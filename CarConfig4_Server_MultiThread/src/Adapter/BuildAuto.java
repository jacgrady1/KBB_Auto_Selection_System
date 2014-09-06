package Adapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import server.AutoServer;

public class BuildAuto extends ProxyAutomobile 
    implements CreateAuto, UpdateAuto,GetGarage,AutoServer
/*
 *BuildAuto class is a empty class that inherited ProxAutomobile and implements 
 *all the interfaces. 
 */
{

	@Override
	public void putSerialIntoGarage(String filename) {
		// TODO Auto-generated method stub
		
	}

	
    

}
