package Adapter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import uitil.FileIO;
import uitil.ReadSource;
import model.*;
public abstract class ProxyAutomobile {

	/*
	 * ProxyAutomobile would be inherited by BuildAuto, 
	 * This Abstract class contains all the method
	 * from the two interfaces. So extra funtions 
	 * could be added by simply edit methods here
	 * */
	
	private static Automobile a1;
	
	
	private Map <String,Automobile> garage = new LinkedHashMap<String,Automobile>();

    
	public Automobile getNewInputAuto(){
		return a1;
	}
	//getter of the LinkedHashMap
	public synchronized Map<String, Automobile> getGarage() {
		return garage;
	}

//	public synchronized void buildAuto(String filename){
//		ReadSource reader = new ReadSource();
//        a1=reader.readFile(filename);
//        garage.put(a1.getModel(), a1);
//	}
	
	public synchronized void buildAuto(String filename){
		// test file name have suffix
		
		if (filename.contains(".")){}
		else{throw new IllegalArgumentException("file name"+
		filename+"does't contain suffix");}
     
		
		String[] parts = filename.split("[.]");
		
		String name=parts[0];
		String type=parts[1];
		
		if( type.equals("txt")) // txt files 
		{
		ReadSource reader = new ReadSource();
        a1=reader.readFile(filename);
        garage.put(a1.getModel(), a1);
		}
		
		else if (type.equals("properties")) // properties files
		{
			FileIO reader=new FileIO();
			a1=reader.ProperToAuto(reader.ReadProperties(filename)); 
			garage.put(a1.getModel(), a1);
		}
		
		else if (type.equals("ser")) // properties files
		{
			FileIO reader=new FileIO();
			a1=reader.DeserializeAuto(filename);
			//a1=reader.ProperToAuto(reader.ReadProperties(filename)); 
			garage.put(a1.getModel(), a1);
		}
		
	}


    public void printAuto(String modelname) {
	    a1=garage.get(modelname);
	    a1.print();
}

	public void updateOptionSetName(String modelname, String OptionSetname,
			String newName) {
		a1=garage.get(modelname);
        a1.changeOptionsetName(OptionSetname, newName);		
	}

	public void updateOptionPrice(String modelname, String OptionSetName,
			String OptionName, float newPrice) 
	{
	   a1=garage.get(modelname);
	   a1.changeOptionPrice(OptionSetName, OptionName, newPrice);
	}
	public void putAutoFromPropertyIntoGarage(String filename)
	{
		this.buildAuto(filename);
		
	}
	
	public void putAutoFromPropertyObjectIntoGarage(Properties prop){
		FileIO reader=new FileIO();
		a1=reader.ProperToAuto(prop);
		garage.put(a1.getModel(), a1);
	}

}
 