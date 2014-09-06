package uitil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.Automobile;

public class FileIO {
/*
 * This class contains Serialize and deserialize method for automobile 
 * and readProperter to get property from file
 * and propertyToAuto to generate a auto from properties
 */
	
	
	public void SerializeAuto(Properties prop){
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("auto.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(prop);
	         //out.close();
	         //fileOut.close();
	         
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	
	public void SerializeAutoMobile(Automobile auto){
		try
	      {
	         FileOutputStream fileOut =
	         new FileOutputStream("auto.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(auto);
	         out.close();
	         fileOut.close();
	         
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	}
	public Automobile DeserializeAuto(String filename)
	{
		Automobile auto = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream(filename);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         Object obj= in.readObject();
	         auto = (Automobile) obj ;
	         in.close();
	         fileIn.close();
	         return auto;
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return null;
	      }catch(ClassNotFoundException c)
	      {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         return null;
	      }
	     
	}
	
	
	public Properties ReadProperties(String filename)
	{
		Properties props = new Properties();
		try {
	         InputStream in = new BufferedInputStream (new FileInputStream(filename));
	         props.load(in);
	        } catch (Exception e) {
	         e.printStackTrace();
	        }
		return props;
	}
	
	public static Automobile ProperToAuto(Properties props) {
	    Automobile auto = new Automobile();  
	    String CarMake = props.getProperty("CarMake");
	    if (!CarMake.equals(null))
	    {
	    	auto.setMake(CarMake); // set auto make
	        String CarModel = props.getProperty("CarModel");
	        auto.setModel(CarModel); // set auto model
	        
	        String CarBasePrice= props.getProperty("CarBasePrice");
	        auto.setBasePrice(Integer.parseInt(CarBasePrice)); // set auto base price
            
	        String regOpSet="^Option[1-9]$";  // Optionset filter
	        Pattern patternOpSet= Pattern.compile(regOpSet);
	        
	        String regOpt = "^OptionValue[1-9][a-z]$";
	        Pattern patternOpt=Pattern.compile(regOpt);
	        
	        String regPrice = "^PriceOptionValue[1-9][a-z]$";
	        Pattern patternPrice=Pattern.compile(regPrice);
	        
	        Enumeration en = props.propertyNames();
            ArrayList<String> optionSetPool = new ArrayList<String>();
            ArrayList<String> optionPool= new ArrayList<String>();
            ArrayList<String> optionPricePool= new ArrayList<String>();

	        while(en.hasMoreElements())
	         {
	        	String key=(String)en.nextElement();
	        	 Matcher matcherOpset=patternOpSet.matcher(key);
	        	 Matcher matcherOption=patternOpt.matcher(key);
	        	 Matcher matcherOptionPrice=patternPrice.matcher(key);
	        	 
	        	 if (matcherOpset.find()) {// matcher.matchers() 
	                 String find = matcherOpset.group();

	                 optionSetPool.add(find);
	             }
	        	 
	        	 if (matcherOption.find()) {// matcher.matchers() 
	                 String find1 = matcherOption.group();
	                 optionPool.add(find1);
	             }
	        	 
	        	 if (matcherOptionPrice.find()){
	        		 String find2 = matcherOptionPrice.group();
	        		 optionPricePool.add(find2);
	        	 }
	         }
	        
	        // here I get all the keys containing in three groups
	        // they are: optionSetPool optionPool optionPricePool
	        
	        int i=0; //index of optionSet
	        for (String s: optionSetPool) // s is option1-4
	        {   
	        	auto.addOptionSet();
                auto.setOptionsetName(i, props.getProperty(s));
                i++;
                String[] parts = s.split("Option");
        		String num=parts[1];  // num = 1 ,2 ,3 
                
        		int j=0; // index of option in a set

                for ( String v : optionPool) //v is optionValue1a-4z
                {
                	String r="^OptionValue"+num+"[a-z]";
                    Pattern p= Pattern.compile(r);
   	        	    Matcher m=p.matcher(v);
   	        	    if (m.find())
   	        	    {
   	        	    	auto.addOption(i-1);
   	        	    	auto.setOptionName(i-1, props.getProperty(v), j);
   	        	    	j++;
   	        	    	String [] part=v.split("OptionValue");
   	        	    	String label=part[1]; //lable is 1a 3b
   	        	    	
   	        	    	for (String c : optionPricePool)
   	        	    	{
   	        	    		if( c.endsWith(label)) 
   	        	    	    {
   	        	    		auto.setOptionPrice(i-1, Float.parseFloat(props.getProperty(c)), j-1);
   	        	    		}
   	        	    	}
   	        	    }
                }
	        }
	    }
	    return auto;
	}

}



