package scale;

import java.util.LinkedHashMap;
import java.util.Map;

import Adapter.BuildAuto;
import model.Automobile;

public class EditOptions implements Runnable {
    //private Map <String,Automobile> garage = new LinkedHashMap<String,Automobile>(); 
	
	private String modelname; 
	private String optionSetname;
	private String optionName;
    private Map<String,Automobile> garage;

  

    
    public EditOptions(String modelname, String optionSetname, String optionName,
    		Map<String,Automobile> garage) {
		this.modelname = modelname;
		this.optionSetname = optionSetname;
		this.optionName = optionName;
		this.garage = garage;
	}

	@Override
	public void run() {
		synchronized(this){
		while(true)
		{
		garage.get(modelname).setOptionChoice(optionSetname, optionName);	
		try {
			Thread.sleep(1000);
			
		}
		catch(Exception e){}
		
		String printit=garage.get(modelname).getOptionChoice(optionSetname); // user's choice of a optionset 
		System.out.println(Thread.currentThread().getName()+printit);
		
		}
	}
	}
	}
