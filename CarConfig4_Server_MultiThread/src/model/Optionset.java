package model;

import java.util.ArrayList;

import javax.swing.text.html.Option;

public class Optionset {
/*
 * Optionset class is updated to corperate ArrayList of option
 */
	private String name;
//	private ArrayList<Option> optArray = new ArrayList<Option>();
	private ArrayList<Option> optArray;
	private Option setOption;
	private Option [] opt;
	
	
	protected Optionset(){
         this.optArray = new ArrayList<Option>();

	}
	protected Optionset(String name, ArrayList<model.Optionset.Option> opt) {
		this.name = name;
		this.optArray= opt;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	protected ArrayList<model.Optionset.Option> getOptArray(){
		return optArray;
	}
	
	protected void addOption(){
//		System.out.println("before addOption!");
		model.Optionset.Option op=new model.Optionset.Option();
		optArray.add(op);
	}
	
	protected void setOptArray(ArrayList<Option> optArray) {
		this.optArray = optArray;
	}
	
	protected Option findOption(String option)
	{
		for( int i =0;i<optArray.size();i++)
		{
			if (optArray.get(i).getName().equals(option))
			{
				//System.out.println(option);
				return optArray.get(i);
			}
		}
		return null;
	}
	
	protected boolean hasOption(String option)
	{
		for( int i =0;i<optArray.size();i++)
		{
			if (optArray.get(i).getName().equals(option))
			{
				//System.out.println(option);
				return true;
			}
		}
		return false;
	}
	
	protected Option getOptionChoice(){
		//System.out.println("getting option choice");
		return this.setOption;
	}
	protected void setOptionChoice(String optionName){
        for (int i=0; i<optArray.size();i++)
        {
        if (optArray.get(i).getName().equals(optionName))
          { 
        	//System.out.println("setting option choice");
        	this.setOption=optArray.get(i);
          }	
        }
	}

	public class Option{
		
		protected Option(){}
        protected Option(String name){
			
		}
		protected Option(String name, Float price) 
	    {
			this.name = name;
			this.price = price;
		}
	    
		private String name;
	    private Float price;
	    protected String getName() {
			return name;
		}
	    protected void setName(String name) {
			this.name = name;
		}
	    protected Float getPrice() {
			return price;
		}
	    protected void setPrice(Float price) {
			this.price = price;
		}
	    
	    
	}
}
