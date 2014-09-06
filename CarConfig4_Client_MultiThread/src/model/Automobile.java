package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.Optionset.Option;

public class Automobile implements Serializable
/*
 * Automobile is changed to cooperate the new data structure,
 * ArrayList of optinsets
 * */
{   
	private static final long serialVersionUID = 1L;  
    private String make;
    private String model;
    private int basePrice;
    private ArrayList<Optionset> optionSets;
    private Option choice;
	
    public Automobile(){
        this.optionSets = new ArrayList<Optionset>();

    }
    public Automobile(String make, String model, int basePrice,
			ArrayList<Optionset> optionSets) {
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		this.optionSets = optionSets;
		this.choice=choice;
	}
	public synchronized String getMake() {
		return make;
	}
	public synchronized void setMake(String make) {
		this.make = make;
	}
	public synchronized String getModel() {
		return model;
	}
	public synchronized void setModel(String model){
		this.model=model;
	}
	public synchronized float getBasePrice(){
		return basePrice;
	}
	public synchronized void setBasePrice(int basePrice){
		this.basePrice=basePrice;
	}
    	
	public synchronized ArrayList<Optionset> getOptionSets() {
		return optionSets;
	}
	public synchronized void setOptionSets(ArrayList<Optionset> optionSets) {
		this.optionSets = optionSets;
	}
	
	public synchronized void addOptionSet() {
		Optionset ops=new Optionset();
		optionSets.add(ops);	
	}
	public synchronized void addOption(int indexSet){
		optionSets.get(indexSet).addOption();
	}
	
	public synchronized void setOptionsetName(int index, String name){ // this is for the first set of optionsetName
		optionSets.get(index).setName(name);
	} 
	
	public synchronized void changeOptionsetName(String oldName,String newName){ // this is for changing the old name
		this.findOptionSet(oldName).setName(newName);
	}
	
	
	public synchronized void setOptionName(int indexSet, String optionName,int index){ // this is for the first time
		optionSets.get(indexSet).getOptArray().get(index).setName(optionName);
	}
	
	public synchronized void setOptionPrice(int indexSet, float optionPrice,int index){ // this is for the first time
		optionSets.get(indexSet).getOptArray().get(index).setPrice(optionPrice);
	}
    
	public synchronized void changeOptionPrice(String optionsetName, String optionName,float optionPrice){ // this is for changing
		this.findOptionSet(optionsetName).findOption(optionName).setPrice(optionPrice);;
	}
	
	public synchronized Optionset findOptionSet(String name) // find optionset by name
	{
		for (int i=0;i<optionSets.size();i++)
		{
			if (optionSets.get(i).getName().equals(name))
			return optionSets.get(i);
		}
	return null;
	}
	
	public synchronized void setOptionChoice(String setName,String optionName) {
//		try{
//		   wait();	
//		}
//		catch(InterruptedException e){}
		
		for(int i=0;i<optionSets.size();i++)
		{
			if (optionSets.get(i).getName().equals(setName))
			{
				optionSets.get(i).setOptionChoice(optionName);
			}
		}
		
//		try {Thread.sleep(10);}
//		catch(Exception e){System.out.println(e.getMessage());}
//		
//		notify();
	}
    
	public synchronized String getOptionChoice(String setName)
	{
//		try{
//			wait();
//		}
//		catch(InterruptedException e){}
		for(int i=0;i<optionSets.size();i++)
		{
			if (optionSets.get(i).getName().equals(setName))
			{
				choice=optionSets.get(i).getOptionChoice();
			}
		}
//		notify();
		return choice.getName();
		
	}
	
	public synchronized float getOptionChoicePrice(String setName)
	{
		for (int i=0;i<optionSets.size();i++)
		{
			if (optionSets.get(i).getName().equals(setName))
			{
				choice=optionSets.get(i).getOptionChoice();
			}
		}
		return choice.getPrice();
	}

	public synchronized void print(){
    	System.out.println("Make: "+make);
    	System.out.println("Model: "+model);
    	System.out.println("Baseprice: " + basePrice);
    	for(int i=0;i<optionSets.size();i++){
    	System.out.print(optionSets.get(i).getName());
    	System.out.print(" : ");
    	for (int j=0;j<optionSets.get(i).getOptArray().size();j++){
    		System.out.print(optionSets.get(i).getOptArray().get(j).getName()+" |"
    				+optionSets.get(i).getOptArray().get(j).getPrice()+"   ");
    	}
    	System.out.println();
    	}
    	
    }
}
    
    
    

